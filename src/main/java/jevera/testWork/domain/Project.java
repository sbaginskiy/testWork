package jevera.testWork.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@Entity
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @NotNull
    private String description;
    private String client;
    @ManyToOne
    @NotNull
    @JsonIgnore
    private Employee productOwner;
    @ManyToOne
    @NotNull
    @JsonIgnore
    private Employee accountManager;
    @ManyToOne
//TODO  ----  @NotNull  -----
//misunderstandings in business logic, so far I decided to comment out
    private Team team;
    private Date plannedWorkStart;
    private Date plannedWorkFinish;
    private Date actualWorkStart;
    private Date actualWorkFinish;
    @Enumerated(EnumType.STRING)
    @NotNull
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

    public Project team(Team team) {
        this.setTeam(team);
        return this;
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
