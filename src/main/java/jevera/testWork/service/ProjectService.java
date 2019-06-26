package jevera.testWork.service;

import jevera.testWork.domain.Dto.EmployeeDto;
import jevera.testWork.domain.Employee;
import jevera.testWork.domain.Project;
import jevera.testWork.domain.Team;
import jevera.testWork.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeService employeeService;

    public List<Project> findByTeam(Team team) {
        return projectRepository.findByTeam(team);
    }

//    public List<Project> findByEmployee(EmployeeDto employeeDto) {
//        Employee employee = employeeService.findByFullName(employeeDto.getFullName());
//
//
//    }
}
