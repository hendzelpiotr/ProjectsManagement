package com.project.java.prz.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @OneToMany(mappedBy = "project")
    private List<UserProject> userProjects;

}
