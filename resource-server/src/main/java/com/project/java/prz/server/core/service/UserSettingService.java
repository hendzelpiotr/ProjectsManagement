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

    UserSettingDTO getUserSettingByName(List<UserSetting> userSettingList, SettingName settingName);

    List<UserSettingDTO> getUserSettings(String login);

    UserSettingDTO getUserSettingByNameAndLogin(String login, SettingName scheduledCompletionDate);

    boolean isAfterScheduledCompletionDateTime(LocalDate date);
}
