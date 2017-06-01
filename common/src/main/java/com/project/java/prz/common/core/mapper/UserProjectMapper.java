package com.project.java.prz.common.core.mapper;

import com.project.java.prz.common.core.domain.general.UserProject;
import com.project.java.prz.common.core.dto.UserProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by Piotr on 03.04.2017.
 */
@Mapper(uses = {ProjectMapper.class, UserDetailsMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProjectMapper {

    UserProjectMapper INSTANCE = Mappers.getMapper(UserProjectMapper.class);

    @Mappings({
            @Mapping(source = "project", target = "projectDTO"),
            @Mapping(source = "userDetails", target = "userDetailsDTO")
    })
    UserProjectDTO convertToDTO(UserProject userProject);

    @Mappings({
            @Mapping(source = "projectDTO", target = "project"),
            @Mapping(source = "userDetailsDTO", target = "userDetails")
    })
    UserProject convertToEntity(UserProjectDTO userProjectDTO);

    List<UserProjectDTO> convertToDTOs(List<UserProject> userProjects);

    List<UserProject> convertToEntities(List<UserProjectDTO> projectDTOs);

}
