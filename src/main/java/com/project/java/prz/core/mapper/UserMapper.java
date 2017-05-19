package com.project.java.prz.core.mapper;

import com.project.java.prz.core.domain.User;
import com.project.java.prz.web.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by Piotr on 03.04.2017.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO convertToDTO(User user);

    User convertToEntity(UserDTO userDTO);

    List<UserDTO> convertToDTOs(List<User> users);

    List<User> convertToEntities(List<UserDTO> userDTOs);

}
