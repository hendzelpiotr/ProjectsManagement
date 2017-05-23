package com.project.java.prz.common.core.mapper;

import com.project.java.prz.common.core.domain.security.Role;
import com.project.java.prz.common.core.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by Piotr on 03.04.2017.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO convertToDTO(Role role);

    Role convertToEntity(RoleDTO roleDTO);

    List<RoleDTO> convertToDTOs(List<Role> roles);

    List<Role> convertToEntities(List<RoleDTO> roleDTOs);

}
