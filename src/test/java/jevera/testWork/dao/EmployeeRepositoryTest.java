package jevera.testWork.dao;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import jevera.testWork.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    @Transactional
    public void findByFullName() {

        Employee test1 = new Employee().fullName("Sergey Baginskiy");
        Employee test2 = new Employee().fullName("Yarik Ctarshuy");
        employeeRepository.saveAll(asList(test1, test2));

        Employee result = employeeRepository.findByFullName("Sergey Baginskiy");
        assertEquals(test1, result);

    }
}