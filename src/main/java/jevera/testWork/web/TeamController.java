package jevera.testWork.web;

import jevera.testWork.domain.Dto.ETPDto;
import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Team;
import jevera.testWork.service.TeamService;
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

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public Team create(@RequestBody TeamDto teamDto) {
        Team team = modelMapper.map(teamDto, Team.class);
        return teamService.save(team);
    }

    @GetMapping("/")
    public List<Team> getAll() {
        return teamService.findAll();
    }

    @GetMapping("/{id}")
    public Team findOneById(@PathVariable Long id) {
        return teamService.findById(id);
    }

    @PutMapping("/{id}")
    public Team update(@PathVariable("id") Team team, @RequestBody TeamDto teamDto) {
        return teamService.update(team, teamDto);
    }

    @PutMapping("/{id}/addEmployee")
    public TeamDto addEmployee(@PathVariable("id") Team team, @RequestBody ETPDto etpDto) {
        return teamService.addEmployee(team, etpDto);
    }

    @PutMapping("/{id}/addEmployeeList")
    public Team addEmployeeList(@PathVariable("id") Team team, @RequestBody List<ETPDto> etpDtoList) {
        return teamService.addEmployeeList(team, etpDtoList);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teamService.delete(id);
    }
}
