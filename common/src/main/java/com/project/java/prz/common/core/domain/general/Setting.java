package com.project.java.prz.common.core.domain.general;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.java.prz.common.core.converter.SettingNameConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by phendzel on 6/9/2017.
 */
@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Convert(converter = SettingNameConverter.class)
    private SettingName name;
    @NotNull
    private String dataType;
    @OneToMany(mappedBy = "setting")
    private List<UserSetting> userSettings;

}
