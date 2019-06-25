package jevera.testWork.web;

import jevera.testWork.domain.Dto.ETPDto;
import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Employee;
import jevera.testWork.domain.Team;
import jevera.testWork.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findAll")
    public List<Team> findAll() {
        return teamService.findAll();
    }

    @GetMapping("/findByName")
    public Team findByName(String name) {
        return teamService.findByFullName(name);
    }

    @PostMapping("/create")
    public Team create(@RequestBody TeamDto teamDto) {
        Team team = modelMapper.map(teamDto, Team.class);
        return teamService.save(team);
    }

    @PostMapping("/update/{id}")
    public Team update(@PathVariable("id") Team team,@RequestBody TeamDto teamDto) {
        return teamService.update(team, teamDto);
    }

//    @PostMapping("/addEmployee/{id}")
//    public Team addEmployee(@PathVariable("id") Team team, Long employeeId) {
//        return teamService.addEmployee(team, employeeId);
//    }
    @PutMapping("/addEmployeeV2/{id}")
    public TeamDto addEmployeeV2(@PathVariable("id") Team team, @RequestBody ETPDto etpDto, String nameEmployee) {
        return teamService.addEmployeeV2(team, etpDto, nameEmployee);
    }


    @DeleteMapping("/delete")
    public void delete(String teamName) {
        teamService.delete(teamName);
    }
}
