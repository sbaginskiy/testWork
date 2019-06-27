package jevera.testWork.domain.Dto;

import jevera.testWork.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
public class ETPDto {
    private Date since;
    private Date till;
    private float loadFactor;
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
