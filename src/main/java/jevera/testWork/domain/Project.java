package jevera.testWork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
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

}
