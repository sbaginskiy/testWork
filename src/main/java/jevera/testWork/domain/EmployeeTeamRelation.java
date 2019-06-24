package jevera.testWork.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Entity
@Getter @Setter
public class EmployeeTeamRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Team team;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Employee employee;

    private Date since;
    private Date till;
    private float loadFactor;

    public EmployeeTeamRelation team(Team team) {
        this.team = team;
        return this;
    }

    public EmployeeTeamRelation employee(Team team) {
        this.team = team;
        return this;
    }
}
