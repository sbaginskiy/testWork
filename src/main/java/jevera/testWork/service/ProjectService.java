package jevera.testWork.service;

import jevera.testWork.domain.Dto.EmployeeDto;
import jevera.testWork.domain.Dto.ProjectDto;
import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Employee;
import jevera.testWork.domain.Project;
import jevera.testWork.domain.Team;
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

    public List<Project> findAllByEmployeeAndPerio(EmployeeDto employeeDto, Date dateFrom,
                                           Date dateTo) {
        Employee employee = employeeService.findByFullName(employeeDto.getFullName());
        List<Team> teams = teamService.findByEmployee(employee);
        return projectRepository.findAllByTeamsAndPeriod(teams, dateFrom, dateTo);
    }

    public Project addTeam(Project project, TeamDto teamDto) {
         project.setTeam(teamService.findByName(teamDto.getName()));
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

    public void delete(Project project) {
        projectRepository.delete(project);
    }
}
