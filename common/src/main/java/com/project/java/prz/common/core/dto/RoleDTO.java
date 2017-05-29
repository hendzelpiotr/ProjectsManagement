package com.project.java.prz.common.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.java.prz.common.core.domain.security.RoleType;
import lombok.*;

/**
 * Created by Piotr on 03.04.2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO {

    private Integer id;
    private RoleType name;

}
