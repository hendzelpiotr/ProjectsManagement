package com.project.java.prz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<User> users;

}
