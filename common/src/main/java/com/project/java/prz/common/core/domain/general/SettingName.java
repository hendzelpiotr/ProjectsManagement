package com.project.java.prz.common.core.domain.general;

import lombok.Getter;

/**
 * Created by phendzel on 6/9/2017.
 */
@Getter
public enum SettingName {

    SCHEDULED_COMPLETION_DATE("scheduled_completion_date");

    SettingName(String settingName) {
        this.settingName = settingName;
    }

    private String settingName;
}
