package com.project.java.prz.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
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
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern="MM/dd/yyyy hh:mm")
    private LocalDateTime completionDateTime;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern="MM/dd/yyyy hh:mm")
    private LocalDateTime datetimeOfProjectSelection;
    private String programmingLanguage;
    private String technologies;
    private String database;
    private String additionalInformation;
    private String repositoryLink;
    private UserDTO userDTO;
    private ProjectDTO projectDTO;

}
