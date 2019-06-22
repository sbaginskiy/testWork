package jevera.testWork.web;

import jevera.testWork.domain.Employee;
import jevera.testWork.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("api/employee")
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private HttpSession session;

    @PostMapping(value = "/login")
    public Employee login(String name, String test) {
        Employee employee = employeeService.register(new Employee().fullName(name));
        session.setAttribute("user", employee);
        return employee;
    }
    @GetMapping(value = "/findAll")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }
}
