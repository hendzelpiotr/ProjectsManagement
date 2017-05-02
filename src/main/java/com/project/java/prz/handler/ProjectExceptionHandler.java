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
public class ProjectExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(value = ProjectException.class)
    @ResponseBody
    public ResponseEntity<ApiResponseError> projectException(ProjectException projectException) {
        switch (projectException.getFailReason()) {
            case PROJECT_DOES_NOT_EXIST:
                return returnApiResponseError(projectException, HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
