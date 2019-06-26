package jevera.testWork.repository;

import jevera.testWork.domain.Employee;
import jevera.testWork.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByName(String name);

    @Query("select e from employee_team_relation where employee_id = employee.id")
    List<Team> findByEmployee(@Param("employee") Employee employee);

}
