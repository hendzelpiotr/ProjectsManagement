package com.project.java.prz.mapper;

import com.project.java.prz.domain.User;
import com.project.java.prz.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Piotr on 03.04.2017.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO convertToDto(User user);

    User convertToEntity(UserDTO userDTO);

}
