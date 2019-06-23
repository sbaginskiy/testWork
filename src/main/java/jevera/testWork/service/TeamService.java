package jevera.testWork.service;

import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Team;
import jevera.testWork.exception.EntityNotFound;
import jevera.testWork.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public void delete(String name) {
        teamRepository.delete(findByFullName(name));
    }

    public Team findByFullName(String name) {
        return teamRepository.findByName(name).orElseThrow(EntityNotFound::new);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Team update(String name, TeamDto teamDto) {
        Team oldTeam = findByFullName(name);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(teamDto, oldTeam);
        return save(oldTeam);
    }
}
