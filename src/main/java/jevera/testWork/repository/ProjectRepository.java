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

//    @Query("select p from project p where p.team = :team and " +
//            "p.actualWorkStart >= :dateFrom and " +
//            "p.actualWorkStart <= :dateTo or " +
//            "(p.actualWorkEnd >= :dateFrom and p.actualWorkEnd <= :dateTo)")
//     List<Project> findByTeamAndPeriod(@Param("team")Team team,
//                                       @Param("dateFrom") Date from,
//                                       @Param("dateTo") Date to);

}
