package com.project.java.prz.server.core.dao;

import com.project.java.prz.common.core.domain.general.UserSetting;
import com.project.java.prz.common.core.domain.general.SettingName;

import java.util.List;

/**
 * Created by phendzel on 6/9/2017.
 */
public interface UserSettingDao {

    List<UserSetting> getAllGlobalSettings();
    UserSetting getGlobalSetting(SettingName settingName);

}
