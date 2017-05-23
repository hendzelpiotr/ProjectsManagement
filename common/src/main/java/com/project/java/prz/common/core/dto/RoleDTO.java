package com.project.java.prz.common.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.java.prz.common.core.domain.security.RoleType;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Piotr on 03.04.2017.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO {

    private Integer id;
    private RoleType name;

}
