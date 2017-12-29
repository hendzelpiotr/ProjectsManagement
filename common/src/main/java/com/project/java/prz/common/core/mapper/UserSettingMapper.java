package com.project.java.prz.common.core.mapper;

import com.project.java.prz.common.core.domain.general.UserSetting;
import com.project.java.prz.common.core.dto.UserSettingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by phendzel on 6/9/2017.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserSettingMapper {

    UserSettingMapper INSTANCE = Mappers.getMapper(UserSettingMapper.class);

    @Mappings({
            @Mapping(source = "setting", target = "settingDTO"),
            @Mapping(source = "userDetail", target = "userDetailDTO")
    })
    UserSettingDTO convertToDTO(UserSetting userSetting);

    @Mappings({
            @Mapping(source = "settingDTO", target = "setting"),
            @Mapping(source = "userDetailDTO", target = "userDetail")
    })
    UserSetting convertToEntity(UserSettingDTO userSettingDTO);

    List<UserSettingDTO> convertToDTOs(List<UserSetting> userSettings);

    List<UserSetting> convertToEntities(List<UserSettingDTO> userSettingDTOs);

}
