package jevera.testWork.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.*;


@Entity
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", insertable = false, updatable = false)
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeTeamRelation> employeeTeamRelation;

    public Team employeeTeamRelation(EmployeeTeamRelation employeeTeamRelation) {
        this.employeeTeamRelation.add(employeeTeamRelation);
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

    public Set<EmployeeTeamRelation> getEmployeeTeamRelation() {
        return employeeTeamRelation;
    }

    public void setEmployeeTeamRelation(Set<EmployeeTeamRelation> employeeTeamRelation) {
        this.employeeTeamRelation = employeeTeamRelation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id.equals(team.id) &&
                name.equals(team.name) &&
                Objects.equals(employeeTeamRelation, team.employeeTeamRelation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employeeTeamRelation);
    }
}
