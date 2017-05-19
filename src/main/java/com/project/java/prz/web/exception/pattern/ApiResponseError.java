package com.project.java.prz.web.exception.pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Piotr on 13.04.2017.
 */
@Getter
@Setter
@AllArgsConstructor
public class ApiResponseError {

    private String codeOfError;
    private String message;

}
