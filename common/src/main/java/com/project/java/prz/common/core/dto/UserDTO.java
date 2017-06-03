package com.project.java.prz.common.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.project.java.prz.common.core.util.View;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by Piotr on 03.04.2017.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @JsonView(View.SecuredUser.class)
    private Integer id;
    @NotNull
    @JsonView(View.SecuredUser.class)
    private String login;
    @NotNull
    private String password;
    @JsonView(View.SecuredUser.class)
    private Boolean enabled;
    @JsonView(View.SecuredUser.class)
    private RoleDTO roleDTO;

}
