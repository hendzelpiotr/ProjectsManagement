package com.project.java.prz.common.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Piotr on 25.03.2017.
 */
@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String login;
    @NotNull
    private String password;
    private String name;
    @NotNull
    private String surname;
    private Integer laboratoryGroup;
    @NotNull
    private Boolean enabled;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private User professor;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
//    @OneToOne(mappedBy = "user")
//    @JsonIgnore
//    private UserProject userProject;

}
