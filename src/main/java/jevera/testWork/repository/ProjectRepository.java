package jevera.testWork.repository;

import jevera.testWork.domain.Project;
import jevera.testWork.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByTeam(Team team);

    @Query("select p from Project p where p.team in :teams and " +
            "p.actualWorkStart >= :dateFrom and " +
            "p.actualWorkStart <= :dateTo or " +
            "(p.actualWorkFinish >= :dateFrom and p.actualWorkFinish <= :dateTo)")
    List<Project> findAllByTeamsAndPeriod(@Param("teams") List<Team> teams,
                                          @Param("dateFrom") Date dateFrom,
                                          @Param("dateTo") Date dateTo);

    @Query("select p from Project p where p.team = :team and " +
            "p.actualWorkStart >= :dateFrom and " +
            "p.actualWorkStart <= :dateTo or " +
            "(p.actualWorkFinish >= :dateFrom and p.actualWorkFinish <= :dateTo)")
    List<Project> findAllByTeamAndPeriod(@Param("team") Team teams,
                                          @Param("dateFrom") Date dateFrom,
                                          @Param("dateTo") Date dateTo);

    Project findByName(String name);
}
