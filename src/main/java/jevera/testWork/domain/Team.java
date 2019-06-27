package jevera.testWork.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", insertable = false, updatable = false)
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<EmployeeTeamRelation> employeeTeamRelations;

    public Team employeeTeamRelation(EmployeeTeamRelation employeeTeamRelation) {
        this.employeeTeamRelations.add(employeeTeamRelation);
        return this;
    }

    public Team addEmployees(Set<EmployeeTeamRelation> employeeTeamRelations) {
        this.employeeTeamRelations.addAll(employeeTeamRelations);
        return this;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeTeamRelation> getEmployeeTeamRelations() {
        return employeeTeamRelations;
    }

    public void setEmployeeTeamRelations(Set<EmployeeTeamRelation> employeeTeamRelations) {
        this.employeeTeamRelations = employeeTeamRelations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id.equals(team.id) &&
                name.equals(team.name) &&
                Objects.equals(employeeTeamRelations, team.employeeTeamRelations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employeeTeamRelations);
    }
}
