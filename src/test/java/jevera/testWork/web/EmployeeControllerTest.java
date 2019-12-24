//package jevera.testWork.web;
//
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.hamcrest.core.Is.is;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import jevera.testWork.domain.Dto.EmployeeDto;
//import jevera.testWork.domain.Employee;
//import jevera.testWork.service.EmployeeService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.stubbing.Answer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(EmployeeController.class)
//public class EmployeeControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @InjectMocks
//    private EmployeeController employeeController;
//
//    @Mock
//    private EmployeeService employeeService;
//
//    @Test
//    public void register() throws Exception {
//
//        EmployeeDto employeeDto = createEmployeeDto("Sergey", "1234");
//
//        Employee employee = createEmployee("Sergey", "1234");
//
//        ResponseEntity<Employee> responseEntity = new ResponseEntity<>(employee, HttpStatus.OK);
//        when(employeeService.register(employeeDto)).thenReturn(employee);
//
//        mvc.perform(get("/api/employees")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$fullName", is(employee.getFullName())));
//    }
//
//    @Test
//    public void login() {
//    }
//
//    @Test
//    public void findAll() {
//    }
//
//    @Test
//    public void getAllByTeam() {
//    }
//
//    @Test
//    public void update() {
//    }
//
//    @Test
//    public void delete() {
//    }
//
//    private EmployeeDto createEmployeeDto(String fullName, String password) {
//
//            return new EmployeeDto(fullName, password, parseDate("2000-07-25"),"-","-",
//                    "-","-",parseDate("2018-05-25"));
//    }
//    private Employee createEmployee(String fullName, String password) {
//            return new Employee(null, fullName, password, parseDate("2000-07-25"),"-","-",
//                    "-","-",parseDate("2018-05-25"));
//    }
//
//    private Date parseDate(String date) {
//        try {
//            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    }
