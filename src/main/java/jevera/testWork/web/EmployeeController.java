package jevera.testWork.web;

import jevera.testWork.domain.Dto.EmployeeDto;
import jevera.testWork.domain.Employee;
import jevera.testWork.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private HttpSession session;

    @PostMapping("/employees/register")
    public ResponseEntity<Employee> register(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.register(employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/employees/login")
    public ResponseEntity<Employee> login(String login, String password) {
        Employee employee = employeeService.login(login, password);
        session.setAttribute("employee", employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping(value = "/api/employees", params = {"page", "size"})
    public ResponseEntity<List<Employee>> findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Employee> employees = employeeService.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(employees.getContent(), HttpStatus.OK);
    }

    @GetMapping("/api/employees-by-team-id")
    public ResponseEntity<List<Employee>> getAllByTeam(Long teamId) {
        List<Employee> result = employeeService.findAllByTeam(teamId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/api/employees/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Employee employee,
                                           @RequestBody EmployeeDto employeeDto) {
        Employee result = employeeService.updateEmployee(employee, employeeDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/api/employees/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Employee employee) {
        employeeService.delete(employee);
        return ResponseEntity.ok().build();
    }
}

