package com.project.java.prz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Piotr on 03.04.2017.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProjectDTO {

    private Integer id;
    private String mark;
    private LocalDateTime completionDateTime;
    private LocalDateTime datetimeOfProjectSelection;
    private String programmingLanguage;
    private String technologies;
    private String database;
    private String additionalInformation;
    private String repositoryLink;
    private UserDTO userDTO;
    private ProjectDTO projectDTO;

}
