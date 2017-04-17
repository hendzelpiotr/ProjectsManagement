package com.project.java.prz.mapper;

import com.project.java.prz.domain.User;
import com.project.java.prz.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by Piotr on 03.04.2017.
 */
@Mapper(uses = UserProjectMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "userProject", target = "userProjectDTO")
    })
    UserDTO convertToDTO(User user);

    @Mappings({
            @Mapping(source = "userProjectDTO", target = "userProject")
    })
    User convertToEntity(UserDTO userDTO);

    List<UserDTO> convertToDTOs(List<User> users);

    List<User> convertToEntities(List<UserDTO> userDTOs);

}
