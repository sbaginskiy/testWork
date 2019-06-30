package jevera.testWork.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", insertable = false, updatable = false)
    private Long id;
    @Column(unique = true)
    @Size(min = 5, max = 25)
    private String fullName;
    private String passwordHash;
    @NotNull
    private Date dateOfBirth;
    @NotNull
    private String sex;
    private String nationality;
    @NotNull
    private String workLocation;
    @NotNull
    private String currentPosition;
    @NotNull
    private String startYearOfProfExp;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<EmployeeTeamRelation> employeeTeamRelations;

    public Employee employeeTeamRelation(EmployeeTeamRelation employeeTeamRelation) {
        this.employeeTeamRelations.add(employeeTeamRelation);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getStartYearOfProfExp() {
        return startYearOfProfExp;
    }

    public void setStartYearOfProfExp(String startYearOfProfExp) {
        this.startYearOfProfExp = startYearOfProfExp;
    }

    public Set<EmployeeTeamRelation> getEmployeeTeamRelations() {
        return employeeTeamRelations;
    }

    public void setEmployeeTeamRelations(Set<EmployeeTeamRelation> employeeTeamRelations) {
        this.employeeTeamRelations = employeeTeamRelations;
    }

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
