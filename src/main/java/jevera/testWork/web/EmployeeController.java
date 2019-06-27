package jevera.testWork.web;

import jevera.testWork.domain.Dto.EmployeeDto;
import jevera.testWork.domain.Dto.EmployeeRequestDto;
import jevera.testWork.domain.Dto.EmployeeResponseDto;
import jevera.testWork.domain.Dto.TeamDto;
import jevera.testWork.domain.Employee;
import jevera.testWork.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private HttpSession session;
    @Autowired
    private ModelMapper modelMapper;

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

    @GetMapping(value = "/")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping(value = "/{fullName}")
    public Employee findByName(@PathVariable String fullName) {
        return employeeService.findByFullName(fullName);
    }

//    @GetMapping("/getAllByTeamId")
//    public List<EmployeeResponseDto> getAllByTeam(Long teamId) {
//       return employeeService.findAllByTeam(teamId).stream().map(it -> modelMapper.map(it, EmployeeResponseDto.class))
//               .collect(Collectors.toList());
//    }
    @GetMapping("/getAllByTeamId")
    public List<Employee> getAllByTeam(Long teamId) {
       return employeeService.findAllByTeam(teamId);
    }

    @PutMapping(value = "/{id}")
    public Employee update(@PathVariable("id") Employee employee,
                           @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employee, employeeDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Employee employee) {
         employeeService.delete(employee);
    }

}

