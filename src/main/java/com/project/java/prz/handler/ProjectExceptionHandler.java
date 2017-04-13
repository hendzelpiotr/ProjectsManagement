package com.project.java.prz.handler;

import com.project.java.prz.exception.ProjectException;
import com.project.java.prz.exception.pattern.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Piotr on 13.04.2017.
 */
@ControllerAdvice
public class ProjectExceptionHandler {

    @ExceptionHandler(value = ProjectException.class)
    @ResponseBody
    public ResponseEntity<ApiResponseError> projectException(ProjectException projectException) {
        switch (projectException.getFailReason()) {
            case YOU_ALREADY_CHOSE_PROJECT:
                ApiResponseError responseError = new ApiResponseError(HttpStatus.BAD_REQUEST.toString(), projectException.getFailReason().toString());
                return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
