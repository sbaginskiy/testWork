package jevera.testWork.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {

    private Long id;
    private String fullName;
    private EmployeeRequestDto employeeRequestDto;

    public EmployeeRequestDto getEmployeeRequestDto() {
        return employeeRequestDto;
    }

    public void setEmployeeRequestDto(EmployeeRequestDto employeeRequestDto) {
        this.employeeRequestDto = employeeRequestDto;
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
}
