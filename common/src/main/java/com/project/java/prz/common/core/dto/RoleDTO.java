package com.project.java.prz.common.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.project.java.prz.common.core.domain.security.RoleType;
import com.project.java.prz.common.core.util.View;
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
    @JsonView(View.SecuredUser.class)
    private RoleType name;

}
