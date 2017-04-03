package com.project.java.prz.mapper;

import com.project.java.prz.domain.UserProject;
import com.project.java.prz.dto.UserProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Piotr on 03.04.2017.
 */
@Mapper
public interface UserProjectMapper {

    UserProjectMapper INSTANCE = Mappers.getMapper(UserProjectMapper.class);

    UserProjectDTO convertToDto(UserProject userProject);

    UserProject convertToEntity(UserProjectDTO userProjectDTO);

}
