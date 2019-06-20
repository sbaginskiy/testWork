package jevera.testWork.service;

import jevera.testWork.dao.EmployeeRepository;
import jevera.testWork.domain.Dto.EmployeeDto;
import jevera.testWork.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee registerEmployee(EmployeeDto employeeDto) {
        if (isEmployeeAlreadyExist(employeeDto.getFullName())) {
            throw new EmployeeAlreadyExsist(employeeDto.getFullName());
        }


    }

    private boolean isEmployeeAlreadyExist(String fullName) {
        return employeeRepository.findByFullName()
    }
}
