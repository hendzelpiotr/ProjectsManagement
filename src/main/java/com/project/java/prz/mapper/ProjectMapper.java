package com.project.java.prz.mapper;

import com.project.java.prz.domain.Project;
import com.project.java.prz.dto.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Piotr on 02.04.2017.
 */
@Mapper
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDTO convertToDto(Project project);

    Project convertToEntity(ProjectDTO projectDTO);

}
