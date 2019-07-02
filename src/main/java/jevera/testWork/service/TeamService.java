package jevera.testWork.service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import jevera.testWork.domain.Dto.ETRDto;
import jevera.testWork.domain.Dto.EmployeeRequestDto;
import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Employee;
import jevera.testWork.domain.EmployeeTeamRelation;
import jevera.testWork.domain.Team;
import jevera.testWork.exception.EntityNotFound;
import jevera.testWork.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
@Slf4j
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private EntityManager entityManager;



    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team findByName(String name) {
        return teamRepository.findByName(name).orElseThrow(EntityNotFound::new);
    }

    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(EntityNotFound::new);
    }

    public Page<Team> findAll(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    public Team update(Team team, TeamDto teamDto) {
        modelMapper.map(teamDto, team);
        return save(team);
    }

    public Team addEmployee(Team team, ETRDto etpDto) {
        EmployeeTeamRelation employeeTeamRelation = modelMapper.map(etpDto, EmployeeTeamRelation.class);
        Employee employee = employeeService.findById(etpDto.getEmployeeRequestDto().getId());

        employeeTeamRelation.setTeam(team);
        employeeTeamRelation.setEmployee(employee);

        employee.employeeTeamRelation(employeeTeamRelation);
        team.employeeTeamRelation(employeeTeamRelation);
        return save(team);
    }

    public Team addEmployeeList(Team team, List<ETRDto> etpDtos) {
        List<Long> employeeIds = etpDtos.stream().map(ETRDto::getEmployeeRequestDto)
                .map(EmployeeRequestDto::getId)
                .collect(toList());

        Map<Long, ETRDto> employeeIdsAndEtpDtos = IntStream.range(0, (etpDtos.size())).boxed()
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

    public List<Team> findByEmployee(Long id) {
        Employee employee = employeeService.findById(id);
        return teamRepository.findAllByEmployee(employee);
    }

    @Transactional
    @Modifying
    public void delete(Long id) {
        try {
            Query query = entityManager.createQuery(
                    "delete " +
                            "from EmployeeTeamRelation e " +
                            "where e.team.id = :teamId");
            query.setParameter("teamId", id);
            query.executeUpdate();
        } finally {
            entityManager.close();
        }

        log.info("project list {}", projectService.findByTeamId(id).stream().map(it -> it.team(null)).map(it -> projectService.save(it)).collect(toList()));
        teamRepository.deleteById(id);
    }
}
