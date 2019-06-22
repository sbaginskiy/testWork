package jevera.testWork.service;

import static java.nio.charset.StandardCharsets.UTF_8;
import jevera.testWork.domain.Dto.EmployeeDto;
import jevera.testWork.domain.Employee;
import jevera.testWork.exception.EmployeeAlreadyExist;
import jevera.testWork.repository.EmployeeRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee register(EmployeeDto employeeDto) {
        if(isEmployeeExist(employeeDto.getFullName())) {
            throw new EmployeeAlreadyExist(employeeDto.getFullName());
        }
        Employee employee = new Employee().fullName(employeeDto.getFullName())
                .passwordHash(encryptPassword(employeeDto.getPassword()));
        return employeeRepository.save(employee);
    }

    public Employee login(EmployeeDto employeeDto) {
       return employeeRepository.findByFullName(employeeDto.getFullName());

    }


    public List<Employee> findAll() {
       return employeeRepository.findAll();
    }



    private boolean isEmployeeExist(String employee) {
        return false;
    }

    @SneakyThrows
    private static String encryptPassword(String password) {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes(UTF_8));
        return new BigInteger(1, crypt.digest()).toString(16);
    }
}
