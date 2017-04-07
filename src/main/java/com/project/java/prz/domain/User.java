package com.project.java.prz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "enabled")
    private Boolean isEnabled;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private User professor;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private UserProject userProject;

}
