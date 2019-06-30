package jevera.testWork.web;

import jevera.testWork.domain.Dto.ProjectDto;
import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Project;
import jevera.testWork.domain.Team;
import jevera.testWork.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/projects")
    public ResponseEntity<Project> createProject(@RequestBody ProjectDto projectDto) {
        Project project = projectService.save(projectDto);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping(value = "/projects", params = {"page", "size"})
    public ResponseEntity<List<Project>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Project> projects = projectService.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(projects.getContent(), HttpStatus.OK);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        Project project = projectService.findById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/projects-by-team-id")
    public ResponseEntity<List<Project>> getProjectByTeam(Long teamId) {
        List<Project> projects = projectService.findByTeamId(teamId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/projects-by-employee-id-and-period")
    public ResponseEntity<List<Project>> getAllProjectsByEmployeeForPeriod(Long id,
                                                                           String dateFrom,
                                                                           String dateTo) throws Exception {
        List<Project> projects = projectService.findAllByEmployeeAndPeriod(id,
                parseDate(dateFrom), parseDate(dateTo));
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/projects-by-period")
    public ResponseEntity<List<Project>> getAllByPeriod(String dateFrom, String dateTo) throws Exception {
        List<Project> projects = projectService.findAllByPeriod(parseDate(dateFrom), parseDate(dateTo));
        return new ResponseEntity<>(projects, HttpStatus.OK);

    }

    @GetMapping("/projects-by-team-id-and-period")
    public ResponseEntity<List<Project>> getAllByTeamAndPeriod(TeamDto teamDto, String dateFrom, String dateTo) throws Exception {
        List<Project> projects = projectService.findByTeamAndPeriod(modelMapper.map(teamDto, Team.class),
                parseDate(dateFrom), parseDate(dateTo));
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Project project, @RequestBody ProjectDto projectDto) {
        Project result = projectService.update(project, projectDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/projects/{id}/add-team")
    public ResponseEntity<Project> addTeam(@PathVariable("id") Project project, TeamDto teamDto) {
        Project result = projectService.addTeam(project, teamDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.ok().build();
    }

    private Date parseDate(String date) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
}
