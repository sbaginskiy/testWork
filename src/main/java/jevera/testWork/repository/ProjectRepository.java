package jevera.testWork.repository;

import jevera.testWork.domain.Project;
import jevera.testWork.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

     List<Project> findByTeam(Team team);


}
