package com.project.java.prz.common.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private LocalDateTime dateTimeOfProjectSelection;
    private String programmingLanguage;
    private String technologies;
    private String database;
    private String additionalInformation;
    private String repositoryLink;
    private boolean sourceFilesUploaded;
    private UserDetailDTO userDetailDTO;
    private ProjectDTO projectDTO;

}
