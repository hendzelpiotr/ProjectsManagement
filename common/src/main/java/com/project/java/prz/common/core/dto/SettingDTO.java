package com.project.java.prz.common.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.java.prz.common.core.domain.general.SettingName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by phendzel on 6/9/2017.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettingDTO {

    private Integer id;
    private SettingName name;
    private String dataType;

}
