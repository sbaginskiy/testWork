package jevera.testWork.web;

import jevera.testWork.domain.Dto.ETRDto;
import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Team;
import jevera.testWork.service.TeamService;
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

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/teams")
    public ResponseEntity<Team> create(@RequestBody Team team) {
        Team result = teamService.save(team);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/teams", params = {"page", "size"})
    public ResponseEntity<List<Team>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Team> results = teamService.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(results.getContent(), HttpStatus.OK);
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getOneById(@PathVariable Long id) {
        Team result = teamService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/teams-by-employee")
    public ResponseEntity<List<Team>> getByEmployee(Long id) {
        List<Team> result = teamService.findByEmployee(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> update(@PathVariable("id") Team team, @RequestBody TeamDto teamDto) {
        Team result = teamService.update(team, teamDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/teams/{id}/add-employee")
    public ResponseEntity<Team> addEmployee(@PathVariable("id") Team team, @RequestBody ETRDto etpDto) {
        Team result = teamService.addEmployee(team, etpDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/teams/{id}/add-employee-list")
    public ResponseEntity<Team> addEmployeeList(@PathVariable("id") Team team, @RequestBody List<ETRDto> etpDtoList) {
        Team result = teamService.addEmployeeList(team, etpDtoList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/teams/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.ok().build();
    }
}
