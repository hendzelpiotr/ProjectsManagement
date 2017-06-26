package com.project.java.prz.common.core.domain.general;

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
    @NotNull
    private Integer availableProjectsCounter;
    @OneToMany(mappedBy = "project")
    private List<UserProject> userProjects;

}
