package jevera.testWork.domain.Dto;

import jevera.testWork.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
public class ETRDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date since;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date till;
    private float loadFactor;
    private EmployeeRequestDto employeeRequestDto;

    public EmployeeRequestDto getEmployeeRequestDto() {
        return employeeRequestDto;
    }

    public void setEmployeeRequestDto(EmployeeRequestDto employeeRequestDto) {
        this.employeeRequestDto = employeeRequestDto;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getTill() {
        return till;
    }

    public void setTill(Date till) {
        this.till = till;
    }

    public float getLoadFactor() {
        return loadFactor;
    }

    public void setLoadFactor(float loadFactor) {
        this.loadFactor = loadFactor;
    }
}
