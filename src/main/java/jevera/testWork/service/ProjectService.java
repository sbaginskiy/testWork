package jevera.testWork.service;

import jevera.testWork.domain.Project;
import jevera.testWork.domain.Team;
import jevera.testWork.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

//    public List<Project> findByTeamAndTeamPeriod(Team team, Date from, Date to) {
//        return projectRepository.findByTeamAndPeriod(team, from, to);
//    }

}
