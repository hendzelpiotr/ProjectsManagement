package com.project.java.prz.common.core.mapper;

import com.project.java.prz.common.core.domain.general.UserDetails;
import com.project.java.prz.common.core.dto.UserDetailsDTO;
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
public interface UserDetailsMapper {

    UserDetailsMapper INSTANCE = Mappers.getMapper(UserDetailsMapper.class);

    @Mappings({
            @Mapping(source = "professor", target = "professorDTO")
    })
    UserDetailsDTO convertToDTO(UserDetails user);

    @Mappings({
            @Mapping(source = "professorDTO", target = "professor")
    })
    UserDetails convertToEntity(UserDetailsDTO userDTO);

    List<UserDetailsDTO> convertToDTOs(List<UserDetails> users);

    List<UserDetails> convertToEntities(List<UserDetailsDTO> userDTOs);

}
