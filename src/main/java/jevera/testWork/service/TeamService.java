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

import java.util.List;

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

    public TeamDto addEmployee(Team team, ETPDto etpDto, String nameEmployee) {
        EmployeeTeamRelation employeeTeamRelation = modelMapper.map(etpDto, EmployeeTeamRelation.class);
        Employee employee = employeeService.findByFullName(nameEmployee);

        employeeTeamRelation.setTeam(team);
        employeeTeamRelation.setEmployee(employee);

        employee.employeeTeamRelation(employeeTeamRelation);
        team.employeeTeamRelation(employeeTeamRelation);
        teamRepository.save(team);
        return modelMapper.map(team, TeamDto.class);
    }

    public List<Team> findByEmployee(Employee employee) {
        return teamRepository.findAllByEmployee(employee);
    }

//    public TeamDto addEmployees(Team team, ETPDto etpDto, String nameEmployee) {
//        EmployeeTeamRelation employeeTeamRelation = new EmployeeTeamRelation();
//
//        employeeTeamRelation.setSince(etpDto.getSince());
//        employeeTeamRelation.setLoadFactor(etpDto.getLoadFactor());
//        employeeTeamRelation.setTill(etpDto.getTill());
//
//        employeeTeamRelation.setTeam(team);
//
//        Employee employee = employeeService.findByFullName(nameEmployee);
//        employeeTeamRelation.setEmployee(employee);
//
//        Set<EmployeeTeamRelation> set = new HashSet<>();
//        set.add(employeeTeamRelation);
//
//        employee.setEmployeeTeamRelations(set);
//        team.employeeTeamRelation(employeeTeamRelation);
//        teamRepository.save(team);
//        return modelMapper.map(team, TeamDto.class);
//    }


    public void delete(String name) {
        teamRepository.delete(findByFullName(name));
    }
}
