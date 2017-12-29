package com.project.java.prz.server.core.dao;

import com.project.java.prz.common.core.domain.general.SettingName;
import com.project.java.prz.common.core.domain.general.UserSetting;

import java.util.List;

/**
 * Created by phendzel on 6/9/2017.
 */
public interface UserSettingDao {

    UserSetting findGlobalUserSettingBySettingName(SettingName settingName);

    UserSetting findUserSettingBySettingNameAndUserDetailLogin(SettingName settingName, String login);

    List<UserSetting> findAllGlobalUserSettings();
}
