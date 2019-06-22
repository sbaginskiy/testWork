package jevera.testWork.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Employee productOwner;
    @ManyToOne
    private Team team;
    private Date plannedWorkStart;
    private Date plannedWorkFinish;
    private Date actualWorkStart;
    private Date actualWorkFinish;
}
