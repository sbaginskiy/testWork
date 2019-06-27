package jevera.testWork.service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import jevera.testWork.domain.Dto.ETPDto;
import jevera.testWork.domain.Dto.EmployeeRequestDto;
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
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

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

    public Team findByName(String name) {
        return teamRepository.findByName(name).orElseThrow(EntityNotFound::new);
    }

    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(EntityNotFound::new);
    }

    public List<TeamDto> findAll() {
        return teamRepository.findAll().stream()
                .map(it -> modelMapper.map(it, TeamDto.class)).collect(toList());
    }

    public Team update(Team team, TeamDto teamDto) {
        modelMapper.map(teamDto, team);
        return save(team);
    }

    public TeamDto addEmployee(Team team, ETPDto etpDto) {
        EmployeeTeamRelation employeeTeamRelation = modelMapper.map(etpDto, EmployeeTeamRelation.class);
        Employee employee = employeeService.findById(etpDto.getEmployeeRequestDto().getId());

        employeeTeamRelation.setTeam(team);
        employeeTeamRelation.setEmployee(employee);

        employee.employeeTeamRelation(employeeTeamRelation);
        team.employeeTeamRelation(employeeTeamRelation);
        teamRepository.save(team);
        return modelMapper.map(team, TeamDto.class);
    }

    public Team addEmployeeList(Team team, List<ETPDto> etpDtos) {

        List<Long> employeeIds = etpDtos.stream().map(ETPDto::getEmployeeRequestDto)
                .map(EmployeeRequestDto::getId)
                .collect(toList());

        Map<Long, ETPDto> employeeIdsAndEtpDtos = IntStream.range(0, (etpDtos.size())).boxed()
                .collect(toMap(employeeIds::get, etpDtos::get));

        Set<EmployeeTeamRelation> employeeTeamRelations = new HashSet<>();

        for (Long employeeId : employeeIdsAndEtpDtos.keySet()) {
            Employee employee = employeeService.findById(employeeId);
            EmployeeTeamRelation employeeTeamRelation =
                    modelMapper.map(employeeIdsAndEtpDtos.get(employeeId), EmployeeTeamRelation.class)
                    .employee(employee).team(team);
            employee.employeeTeamRelation(employeeTeamRelation);
            employeeTeamRelations.add(employeeTeamRelation);
        }
        team.addEmployees(employeeTeamRelations);
        return save(team);
    }

    public List<Team> findByEmployee(EmployeeRequestDto requestDto) {
        Employee employee = employeeService.findById(requestDto.getId());
        return teamRepository.findAllByEmployee(employee);
    }

    public void delete(Long id) {
        teamRepository.delete(findById(id));
    }
}
