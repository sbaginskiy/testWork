package jevera.testWork.domain.Dto;

import jevera.testWork.domain.Employee;
import jevera.testWork.domain.ProjectStatus;
import jevera.testWork.domain.Team;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private String name;
    @NotNull
    private String description;
    private String client;
    @NotNull
    private Employee productOwner;
    @NotNull
    private Employee accountManager;
    @NotNull
    private Team team;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualWorkStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualWorkFinish;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date plannedWorkStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date plannedWorkFinish;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    public Date getActualWorkStart() {
        return actualWorkStart;
    }

    public void setActualWorkStart(Date actualWorkStart) {
        this.actualWorkStart = actualWorkStart;
    }

    public Date getActualWorkFinish() {
        return actualWorkFinish;
    }

    public void setActualWorkFinish(Date actualWorkFinish) {
        this.actualWorkFinish = actualWorkFinish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Employee getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(Employee productOwner) {
        this.productOwner = productOwner;
    }

    public Employee getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(Employee accountManager) {
        this.accountManager = accountManager;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Date getPlannedWorkStart() {
        return plannedWorkStart;
    }

    public void setPlannedWorkStart(Date plannedWorkStart) {
        this.plannedWorkStart = plannedWorkStart;
    }

    public Date getPlannedWorkFinish() {
        return plannedWorkFinish;
    }

    public void setPlannedWorkFinish(Date plannedWorkFinish) {
        this.plannedWorkFinish = plannedWorkFinish;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
