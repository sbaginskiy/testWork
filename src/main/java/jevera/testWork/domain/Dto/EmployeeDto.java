package jevera.testWork.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @NotNull
    private String fullName;
    @Size(min = 7)
    @NotNull
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startYearOfProfExp;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getStartYearOfProfExp() {
        return startYearOfProfExp;
    }

    public void setStartYearOfProfExp(Date startYearOfProfExp) {
        this.startYearOfProfExp = startYearOfProfExp;
    }
}
