package jevera.testWork.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
@Setter
@Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String fullName;
    private String passwordHash;
    private Date dateOfBirth;
    private String sex;
    private String nationality;
    private String workLocation;
    private String currentPosition;
    private String startYearOfProfExp;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<EmployeeTeamRelation> employeeTeamRelations;

    public Employee fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
    public Employee passwordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }
    public Employee dateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }
    public Employee sex(String sex) {
        this.sex = sex;
        return this;
    }
    public Employee nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }
    public Employee workLocation(String workLocation) {
        this.workLocation = workLocation;
        return this;
    }
    public Employee currentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
        return this;
    }
    public Employee startYearOfProfExp(String startYearOfProfExp) {
        this.startYearOfProfExp = startYearOfProfExp;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id) &&
                fullName.equals(employee.fullName) &&
                dateOfBirth.equals(employee.dateOfBirth) &&
                sex.equals(employee.sex) &&
                Objects.equals(nationality, employee.nationality) &&
                workLocation.equals(employee.workLocation) &&
                currentPosition.equals(employee.currentPosition) &&
                startYearOfProfExp.equals(employee.startYearOfProfExp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
