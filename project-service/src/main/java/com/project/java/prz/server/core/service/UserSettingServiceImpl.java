package com.project.java.prz.server.core.service;

import com.project.java.prz.common.core.domain.general.Setting;
import com.project.java.prz.common.core.domain.general.SettingName;
import com.project.java.prz.common.core.domain.general.UserDetail;
import com.project.java.prz.common.core.domain.general.UserSetting;
import com.project.java.prz.common.core.dto.UserSettingDTO;
import com.project.java.prz.common.core.exception.UserSettingException;
import com.project.java.prz.common.core.mapper.SettingMapper;
import com.project.java.prz.common.core.mapper.UserSettingMapper;
import com.project.java.prz.server.core.dao.UserSettingDao;
import com.project.java.prz.server.core.repository.SettingRepository;
import com.project.java.prz.server.core.repository.UserDetailRepository;
import com.project.java.prz.server.core.repository.UserSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.LocalDate;
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

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private Clock clock;

    @Override
    public UserSettingDTO getUserSettingBySettingName(List<UserSetting> userSettingList, SettingName settingName) {
        UserSetting foundUserSetting = userSettingList
                .stream()
                .filter(userSetting -> (userSetting.getSetting().getName().equals(settingName)))
                .findFirst()
                .orElse(userSettingDao.findGlobalUserSettingBySettingName(settingName));
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
                    .orElse(userSettingDao.findGlobalUserSettingBySettingName(setting.getName()));

            userSettingDTOs.add(UserSettingMapper.INSTANCE.convertToDTO(foundUserSetting));
        });

        return userSettingDTOs;
    }

    @Override
    public UserSettingDTO getUserSettingBySettingNameAndUserDetailLogin(String login, SettingName settingName) {
        UserSetting userSetting = userSettingDao.findUserSettingBySettingNameAndUserDetailLogin(settingName, login);
        return UserSettingMapper.INSTANCE.convertToDTO(userSetting);
    }

    @Override
    public List<UserSettingDTO> getAllGlobalUserSettings() {
        List<UserSetting> allGlobalUserSettings = userSettingDao.findAllGlobalUserSettings();
        return UserSettingMapper.INSTANCE.convertToDTOs(allGlobalUserSettings);
    }

    @Override
    public UserSettingDTO updateUserSetting(UserSettingDTO userSettingDTO, Integer id) {
        UserSetting userSetting = UserSettingMapper.INSTANCE.convertToEntity(userSettingDTO);

        UserSetting updatedUserSetting = userSettingRepository.save(userSetting);

        return UserSettingMapper.INSTANCE.convertToDTO(updatedUserSetting);
    }

    @Override
    public List<UserSettingDTO> saveOrUpdateUserSettings(List<UserSettingDTO> userSettingDTOs) {
        List<UserSetting> userSettings = UserSettingMapper.INSTANCE.convertToEntities(userSettingDTOs);

        List<UserSetting> savedUserSettings = userSettingRepository.save(userSettings);

        return UserSettingMapper.INSTANCE.convertToDTOs(savedUserSettings);
    }

    @Override
    public boolean isAfterScheduledCompletionDateTime(LocalDate date) {
        if (date == null) {
            return false;
        }
        return LocalDate.now(clock).isAfter(date);
    }

    @Override
    public UserSettingDTO saveOrUpdate(String login, UserSettingDTO userSettingDTO) {
        UserSetting userSetting = userSettingDao.findUserSettingBySettingNameAndUserDetailLogin(userSettingDTO.getSettingDTO().getName(), login);

        if (userSetting.getUserDetail() == null) {
            userSetting = new UserSetting();
            userSetting.setSetting(SettingMapper.INSTANCE.convertToEntity(userSettingDTO.getSettingDTO()));
            UserDetail userDetail = userDetailRepository.findOne(login);
            userSetting.setUserDetail(userDetail);
            userSetting.setValue(userSettingDTO.getValue());
        } else if (userSetting.getId().equals(userSettingDTO.getId())) {
            userSetting.setValue(userSettingDTO.getValue());
        } else throw new UserSettingException(UserSettingException.FailReason.YOU_CAN_NOT_UPDATE_USER_SETTING);

        UserSetting savedUserSetting = userSettingRepository.save(userSetting);
        
        return UserSettingMapper.INSTANCE.convertToDTO(savedUserSetting);
    }

}
