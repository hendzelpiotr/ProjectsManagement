package com.project.java.prz.common.core.mapper;

import com.project.java.prz.common.core.domain.general.UserDetail;
import com.project.java.prz.common.core.dto.UserDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by phendzel on 6/1/2017.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDetailMapper {

    UserDetailMapper INSTANCE = Mappers.getMapper(UserDetailMapper.class);

    @Mappings({
            @Mapping(source = "professor", target = "professorDTO")
    })
    UserDetailDTO convertToDTO(UserDetail user);

    @Mappings({
            @Mapping(source = "professorDTO", target = "professor")
    })
    UserDetail convertToEntity(UserDetailDTO userDTO);

    List<UserDetailDTO> convertToDTOs(List<UserDetail> users);

    List<UserDetail> convertToEntities(List<UserDetailDTO> userDTOs);

}
