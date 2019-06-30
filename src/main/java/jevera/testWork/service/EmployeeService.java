package jevera.testWork.service;

import static java.nio.charset.StandardCharsets.UTF_8;

import jevera.testWork.domain.Dto.EmployeeDto;
import jevera.testWork.domain.Employee;
import jevera.testWork.exception.EmployeeAlreadyExist;
import jevera.testWork.exception.EntityNotFound;
import jevera.testWork.exception.UncorrectGrant;
import jevera.testWork.repository.EmployeeRepository;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.persistence.EntityManager;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TeamService teamService;
    @Autowired
    private EntityManager entityManager;

    public Employee save(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    public Employee register(EmployeeDto employeeDto) {
        if (isEmployeeExist(employeeDto.getFullName())) {
            throw new EmployeeAlreadyExist(employeeDto.getFullName());
        }
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setPasswordHash(encryptPassword(employeeDto.getPassword()));
        return save(employee);
    }

    public Employee login(String fullName, String password) {
        return employeeRepository.findByFullName(fullName)
                .filter(employee -> checkPassword(password, employee))
                .orElseThrow(UncorrectGrant::new);
    }

    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee findByFullName(String fullName) {
        return employeeRepository.findByFullName(fullName).orElseThrow(EntityNotFound::new);
    }

    public Employee findById(Long id) {
        return employeeRepository.getOne(id);
    }

    public Employee updateEmployee(Employee employee, EmployeeDto employeeDto) {
        modelMapper.map(employeeDto, employee);
        if (StringUtils.isNotBlank(employeeDto.getPassword())) {
            employee.setPasswordHash(encryptPassword(employeeDto.getPassword()));
        }
        return save(employee);
    }

    public List<Employee> findAllByTeam (Long teamId) {
       return employeeRepository.findAllByTeam(teamService.findById(teamId));
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    //---------------------------------------------------------

    private boolean isEmployeeExist(String employee) {
       return employeeRepository.findByFullName(employee).isPresent();
    }

    private boolean checkPassword(String password, Employee employee) {
        String encryptPassword = encryptPassword(password);
        return encryptPassword.equals(employee.getPasswordHash());
    }

    @SneakyThrows
    private static String encryptPassword(String password) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes(UTF_8));
            return new BigInteger(1, crypt.digest()).toString(16);
        } catch (NoSuchAlgorithmException exception) {
        }
        return null;
    }
}
