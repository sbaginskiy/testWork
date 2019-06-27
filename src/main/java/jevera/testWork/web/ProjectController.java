package jevera.testWork.web;

import jevera.testWork.domain.Dto.ProjectDto;
import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Project;
import jevera.testWork.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findAll")
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/findByName")
    public Project findByName(String name) {
        return projectService.findByName(name);
    }

    @PostMapping("/create")
    public Project create(@RequestBody ProjectDto projectDto) {
        return projectService.save(projectDto);
    }

    @PutMapping("/{id}")
    public Project addTeam(@PathVariable("id") Project project, TeamDto teamDto) {
       return projectService.addTeam(project, teamDto);
    }

    @DeleteMapping("/delete")
    public void delete(Project project) {
        projectService.delete(project);
    }

}
