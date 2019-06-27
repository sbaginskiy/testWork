package jevera.testWork.web;

import jevera.testWork.domain.Dto.EmployeeRequestDto;
import jevera.testWork.domain.Dto.ProjectDto;
import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Project;
import jevera.testWork.domain.Team;
import jevera.testWork.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public Project createProject(@RequestBody ProjectDto projectDto) {
        return projectService.save(projectDto);
    }

    @GetMapping("/")
    public List<Project> getAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @GetMapping("/getByEmployeeAndPeriod")
    public List<Project> getAllProjectsByEmployeeForPeriod(EmployeeRequestDto employeeDto,
                                                           String dateFrom,
                                                           String dateTo) throws Exception{
        return projectService.findAllByEmployeeAndPeriod(employeeDto,
                parseDate(dateFrom), parseDate(dateTo));
    }

    @GetMapping("/getAllByPeriod")
    public List<Project> getAllByPeriod(String dateFrom, String dateTo) throws Exception{
        return projectService.findAllByPeriod(parseDate(dateFrom), parseDate(dateTo));
    }
    @GetMapping("/getAllByTeamAndPeriod")
    public List<Project> getAllByTeamAndPeriod(TeamDto teamDto, String dateFrom, String dateTo) throws Exception{
        return projectService.findByTeamAndPeriod(modelMapper.map(teamDto, Team.class),
                parseDate(dateFrom), parseDate(dateTo));
    }

    @PutMapping("/{id}")
    public Project updateProject (@PathVariable("id") Project project, ProjectDto projectDto) {
    return projectService.update(project, projectDto);
    }

    @PutMapping("/{id}/addTeam")
    public Project addTeam(@PathVariable("id") Project project, TeamDto teamDto) {
       return projectService.addTeam(project, teamDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }

    private Date parseDate(String date) throws Exception{
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
}
