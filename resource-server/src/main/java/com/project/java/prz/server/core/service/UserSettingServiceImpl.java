package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.general.Setting;
import com.project.java.prz.common.core.domain.general.SettingName;
import com.project.java.prz.common.core.domain.general.UserSetting;
import com.project.java.prz.common.core.dto.UserSettingDTO;
import com.project.java.prz.common.core.mapper.UserSettingMapper;
import com.project.java.prz.server.core.dao.UserSettingDao;
import com.project.java.prz.server.core.repository.SettingRepository;
import com.project.java.prz.server.core.repository.UserSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotr on 09.06.2017.
 */
@Service
@Transactional
public class UserSettingServiceImpl implements UserSettingService {

    @Autowired
    private UserSettingDao userSettingDao;

    @Autowired
    private UserSettingRepository userSettingRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Override
    public UserSettingDTO getUserSettingByName(List<UserSetting> userSettingList, SettingName settingName) {
        UserSetting foundUserSetting = userSettingList
                .stream()
                .filter(userSetting -> (userSetting.getSetting().getName().equals(settingName)))
                .findFirst()
                .orElse(userSettingDao.findGlobalUserSettingByName(settingName));
        return UserSettingMapper.INSTANCE.convertToDTO(foundUserSetting);
    }

    @Override
    public List<UserSettingDTO> getUserSettings(String login) {
        List<UserSetting> userSettings = userSettingRepository.findAllByUserDetailLogin(login);
        List<Setting> settings = settingRepository.findAll();
        List<UserSettingDTO> userSettingDTOs = new ArrayList<>();

        settings.forEach(setting -> {
            UserSetting foundUserSetting = userSettings.stream()
                    .filter(userSetting -> setting.getName().equals(userSetting.getSetting().getName()))
                    .findAny()
                    .orElse(userSettingDao.findGlobalUserSettingByName(setting.getName()));

            userSettingDTOs.add(UserSettingMapper.INSTANCE.convertToDTO(foundUserSetting));
        });

        return userSettingDTOs;
    }

}
