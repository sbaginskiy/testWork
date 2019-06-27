package jevera.testWork.repository;

import jevera.testWork.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByFullName(String fullName);

    Optional<Employee> findById(Long id);

    @Query("select e from Employee e join e.employeeTeamRelations ert where ert.team.id = :teamId")
    List<Employee> findAllByTeam(@Param("teamId") Long teamId);

}
