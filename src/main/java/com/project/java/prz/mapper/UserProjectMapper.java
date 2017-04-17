package com.project.java.prz.mapper;

import com.project.java.prz.domain.UserProject;
import com.project.java.prz.dto.UserProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by Piotr on 03.04.2017.
 */
@Mapper(uses = ProjectMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProjectMapper {

    UserProjectMapper INSTANCE = Mappers.getMapper(UserProjectMapper.class);

    @Mappings({
            @Mapping(source = "project", target = "projectDTO")
    })
    UserProjectDTO convertToDTO(UserProject userProject);

    @Mappings({
            @Mapping(source = "projectDTO", target = "project")
    })
    UserProject convertToEntity(UserProjectDTO userProjectDTO);

    List<UserProjectDTO> convertToDTOs(List<UserProject> userProjects);

    List<UserProject> convertToEntities(List<UserProjectDTO> projectDTOs);

}
