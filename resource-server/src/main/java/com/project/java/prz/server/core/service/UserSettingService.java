package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.general.SettingName;
import com.project.java.prz.common.core.domain.general.UserSetting;
import com.project.java.prz.common.core.dto.UserSettingDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Piotr on 09.06.2017.
 */
public interface UserSettingService {

    UserSettingDTO getUserSettingBySettingName(List<UserSetting> userSettingList, SettingName settingName);

    List<UserSettingDTO> getUserSettings(String login);

    UserSettingDTO getUserSettingBySettingNameAndUserDetailLogin(String login, SettingName scheduledCompletionDate);

    List<UserSettingDTO> getAllGlobalUserSettings();

    UserSettingDTO updateUserSetting(UserSettingDTO userSettingDTO, Integer id);

    boolean isAfterScheduledCompletionDateTime(LocalDate date);
}
