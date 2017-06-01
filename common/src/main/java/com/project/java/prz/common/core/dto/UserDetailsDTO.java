package com.project.java.prz.common.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by phendzel on 6/1/2017.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailsDTO {

    private String login;
    private String password;
    private String name;
    private String surname;
    private Integer laboratoryGroup;
    private UserDetailsDTO professorDTO;

}
