package com.project.java.prz.mapper;

import com.project.java.prz.domain.Role;
import com.project.java.prz.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Piotr on 03.04.2017.
 */
@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO convertToDto(Role role);

    Role convertToEntity(RoleDTO roleDTO);

}
