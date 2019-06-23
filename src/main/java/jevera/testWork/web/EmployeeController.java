package jevera.testWork.web;

import jevera.testWork.domain.Dto.EmployeeDto;
import jevera.testWork.domain.Employee;
import jevera.testWork.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private HttpSession session;

    @PostMapping(value = "/register")
    public Employee register(EmployeeDto employeeDto) {
        return employeeService.register(employeeDto);
    }

    @PostMapping(value = "/login")
    public Employee login(String login, String password) {
        Employee employee = employeeService.login(login, password);
        session.setAttribute("employee", employee);
        return employee;
    }

    @GetMapping(value = "/findAll")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping(value = "/{fullName}")
    public Employee findByName(@PathVariable String fullName) {
        return employeeService.findByFullName(fullName);
    }

    @PostMapping(value = "/update")
    public Employee update(@RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto);
    }

//    @PostMapping(value = "/update")
//    public Employee update(EmployeeDto employeeDto) {
//
//        return employeeService.updateEmployee(fullName, employeeDto);
//    }

}

