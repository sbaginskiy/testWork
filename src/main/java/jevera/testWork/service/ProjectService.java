package jevera.testWork.service;

import jevera.testWork.domain.Dto.ProjectDto;
import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Employee;
import jevera.testWork.domain.Project;
import jevera.testWork.domain.Team;
import jevera.testWork.exception.EntityNotFound;
import jevera.testWork.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<Project> findByTeamId(Long id) {
        return projectRepository.findByTeam(id);
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(EntityNotFound::new);
    }

    public List<Project> findAllByEmployeeAndPeriod(Long id, Date dateFrom,
                                                    Date dateTo) {
        List<Team> teams = teamService.findByEmployee(id);
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

    public Page<Project> findAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }

    public Project save(ProjectDto projectDto) {
        Employee accountManager = employeeService.findById(projectDto.getAccountManager().getId());
        Employee productOwner = employeeService.findById(projectDto.getProductOwner().getId());
        Team team = teamService.findById(projectDto.getTeam().getId());
        Project project = modelMapper.map(projectDto, Project.class);
        project.setAccountManager(accountManager);
        project.setProductOwner(productOwner);
        project.setTeam(team);
        return projectRepository.save(project);
    }
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project update(Project project, ProjectDto projectDto) {
        modelMapper.map(projectDto, project);
        return save(project);
    }
    public void delete(Long id) {
        projectRepository.delete(findById(id));
    }
}
