package jevera.testWork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String client;
    @ManyToOne
    private Employee productOwner;
    @ManyToOne
    private Employee accountManager;
    @ManyToOne
    private Team team;
    private Date plannedWorkStart;
    private Date plannedWorkFinish;
    private Date actualWorkStart;
    private Date actualWorkFinish;
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

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

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
