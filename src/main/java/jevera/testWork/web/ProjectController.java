package jevera.testWork.web;

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
    public Project create(@RequestBody Project project) {
        return projectService.save(project);
    }



    @DeleteMapping("/delete")
    public void delete(Project project) {
        projectService.delete(project);
    }

}
