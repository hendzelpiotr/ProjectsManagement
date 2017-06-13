package com.project.java.prz.common.core.mapper;

import com.project.java.prz.common.core.domain.general.Setting;
import com.project.java.prz.common.core.dto.SettingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by phendzel on 6/9/2017.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SettingMapper {

    SettingMapper INSTANCE = Mappers.getMapper(SettingMapper.class);

    SettingDTO convertToDTO(Setting setting);

    Setting convertToEntity(SettingDTO settingDTO);

    List<SettingDTO> convertToDTOs(List<Setting> settings);

    List<Setting> convertToEntities(List<SettingDTO> settingDTOs);

}
