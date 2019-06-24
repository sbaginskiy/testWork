package jevera.testWork.repository;

import jevera.testWork.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByFullName(String fullName);

    Optional<Employee> findById(Long id);
}
