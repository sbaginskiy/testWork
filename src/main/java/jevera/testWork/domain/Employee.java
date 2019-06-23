package jevera.testWork.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

@Entity
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getStartYearOfProfExp() {
        return startYearOfProfExp;
    }

    public void setStartYearOfProfExp(String startYearOfProfExp) {
        this.startYearOfProfExp = startYearOfProfExp;
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
