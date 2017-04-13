package com.project.java.prz.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Piotr on 25.03.2017.
 */
@Entity
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    private Integer availableProjectsCounter;
    @OneToMany(mappedBy = "project")
    private List<UserProject> userProjects;

}
