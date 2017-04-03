package com.project.java.prz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Piotr on 03.04.2017.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Integer id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private Integer laboratoryGroup;
    private UserDTO professor;
    private RoleDTO role;

}
