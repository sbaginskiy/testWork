package jevera.testWork.service;

import jevera.testWork.domain.Dto.ETPDto;
import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Employee;
import jevera.testWork.domain.EmployeeTeamRelation;
import jevera.testWork.domain.Team;
import jevera.testWork.exception.EntityNotFound;
import jevera.testWork.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ModelMapper modelMapper;

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team findByFullName(String name) {
        return teamRepository.findByName(name).orElseThrow(EntityNotFound::new);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Team update(Team team, TeamDto teamDto) {
        modelMapper.map(teamDto, team);
        return save(team);
    }

    public Team addEmployee(Team team, Long employeeId) {
        EmployeeTeamRelation employeeTeamRelation = new EmployeeTeamRelation().team(team)
                .employee(employeeService.findById(employeeId));

        team.employeeTeamRelation(employeeTeamRelation);
        return team;
    }

    public TeamDto addEmployeeV2(Team team, ETPDto etpDto, String nameEmployee) {
        EmployeeTeamRelation employeeTeamRelation = new EmployeeTeamRelation();

        employeeTeamRelation.setSince(etpDto.getSince());
        employeeTeamRelation.setLoadFactor(etpDto.getLoadFactor());
        employeeTeamRelation.setTill(etpDto.getTill());

        employeeTeamRelation.setTeam(team);

        Employee employee = employeeService.findByFullName(nameEmployee);
        employeeTeamRelation.setEmployee(employee);

        Set<EmployeeTeamRelation> set = new HashSet<>();
        set.add(employeeTeamRelation);

        employee.setEmployeeTeamRelations(set);

        return modelMapper.map(team, TeamDto.class);
    }


    public void delete(String name) {
        teamRepository.delete(findByFullName(name));
    }
}
