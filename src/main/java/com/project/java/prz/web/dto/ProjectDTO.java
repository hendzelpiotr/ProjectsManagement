package com.project.java.prz.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Piotr on 02.04.2017.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDTO {

    private Integer id;
    private String name;
    private String description;
    private Integer availableProjectsCounter;

}
