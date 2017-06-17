package com.project.java.prz.common.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by phendzel on 6/9/2017.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSettingDTO {

    private Integer id;
    private String value;
    private SettingDTO settingDTO;
    private UserDetailDTO userDetailDTO;

}
