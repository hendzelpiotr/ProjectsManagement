package com.project.java.prz.common.core.domain.general;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by phendzel on 6/1/2017.
 */
@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetail {

    @Id
    @NotNull
    private String login;
    private String name;
    private String surname;
    private Integer laboratoryGroup;
    @OneToOne(mappedBy = "userDetail")
    @JsonIgnore
    private UserProject userProject;
    @OneToMany(mappedBy = "userDetail")
    private List<UserSetting> userSettings;


}
