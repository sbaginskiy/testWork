package jevera.testWork.dao;

import jevera.testWork.domain.Employee;

public interface EmployeeRepository {

    Employee findByFullName(String full)

}
