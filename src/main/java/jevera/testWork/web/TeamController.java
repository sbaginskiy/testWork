package jevera.testWork.web;

import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Team;
import jevera.testWork.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/findAll")
    public List<Team> findAll() {
        return teamService.findAll();
    }

    @GetMapping("/findByName")
    public Team findByName(String name) {
        return teamService.findByFullName(name);
    }

    @PostMapping("/create")
    public Team create(@RequestBody Team team) {
        return teamService.save(team);
    }

    @PostMapping("/update")
    public Team update(String teamName, TeamDto teamDto) {
        return teamService.update(teamName, teamDto);
    }

    @DeleteMapping("/delete")
    public void delete(String teamName) {
        teamService.delete(teamName);
    }
}
