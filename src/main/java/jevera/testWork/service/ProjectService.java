package jevera.testWork.service;

import jevera.testWork.domain.Dto.EmployeeRequestDto;
import jevera.testWork.domain.Dto.ProjectDto;
import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Employee;
import jevera.testWork.domain.Project;
import jevera.testWork.domain.Team;
import jevera.testWork.exception.EntityNotFound;
import jevera.testWork.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ModelMapper modelMapper;

    public List<Project> findByTeam(Team team) {
        return projectRepository.findByTeam(team);
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(EntityNotFound::new);
    }

    public List<Project> findAllByEmployeeAndPeriod(EmployeeRequestDto employeeDto, Date dateFrom,
                                                    Date dateTo) {
        Employee employee = employeeService.findByFullName(employeeDto.getFullName());
        List<Team> teams = teamService.findByEmployee(employee);
        return projectRepository.findAllByTeamsAndPeriod(teams, dateFrom, dateTo);
    }

    public List<Project> findAllByPeriod(Date dateFrom, Date dateTo) {
        return projectRepository.findAllByPeriod(dateFrom, dateTo);
    }

    public List<Project> findByTeamAndPeriod(Team team, Date dateFrom, Date dateTo) {
        return projectRepository.findAllByTeamAndPeriod(team,dateFrom, dateTo);
    }

    public Project addTeam(Project project, TeamDto teamDto) {
         project.setTeam(teamService.findById(teamDto.getId()));
         return project;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }

    public Project save(ProjectDto projectDto) {
        return projectRepository.save(modelMapper.map(projectDto, Project.class));
    }

    public Project update(Project project, ProjectDto projectDto) {
        modelMapper.map(projectDto, project);
        return project;
    }

    public void delete(Long id) {
        projectRepository.delete(findById(id));
    }
}
