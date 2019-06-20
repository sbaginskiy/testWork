package jevera.testWork.dao.jpa;

import jevera.testWork.domain.Employee;
import jevera.testWork.domain.EmployeeTeamRelation;
import jevera.testWork.domain.Project;
import jevera.testWork.domain.Team;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateConfiguration {
    @Bean
    public EntityManagerFactory createEntityManagerFactory(DataSourceProperties dataSourceProperties) {

        Map<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.LOADED_CLASSES, Arrays.asList(Employee.class,
                EmployeeTeamRelation.class,
                Team.class,
                Project.class));
        properties.put(AvailableSettings.JPA_JDBC_DRIVER, dataSourceProperties.getDriverClassName());
        properties.put(AvailableSettings.URL, dataSourceProperties.getUrl());
        properties.put(AvailableSettings.USER, dataSourceProperties.getUsername());
        properties.put(AvailableSettings.PASS, dataSourceProperties.getPassword());
        properties.put(AvailableSettings.PHYSICAL_NAMING_STRATEGY, SpringPhysicalNamingStrategy.class.getCanonicalName());

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("auction", properties);
        return entityManagerFactory;
    }
}
