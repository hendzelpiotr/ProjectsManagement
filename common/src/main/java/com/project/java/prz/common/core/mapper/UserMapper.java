package com.project.java.prz.common.core.mapper;

import com.project.java.prz.common.core.domain.security.User;
import com.project.java.prz.common.core.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by Piotr on 03.04.2017.
 */
@Mapper(uses = RoleMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "role", target = "roleDTO")
    })
    UserDTO convertToDTO(User user);

    @Mappings({
            @Mapping(source = "roleDTO", target = "role")
    })
    User convertToEntity(UserDTO userDTO);

    List<UserDTO> convertToDTOs(List<User> users);

    List<User> convertToEntities(List<UserDTO> userDTOs);

}
