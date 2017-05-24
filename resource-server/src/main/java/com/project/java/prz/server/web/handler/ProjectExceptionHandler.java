package com.project.java.prz.server.web.handler;

import com.project.java.prz.common.core.exception.ProjectException;
import com.project.java.prz.common.core.exception.pattern.ApiResponseError;
import com.project.java.prz.common.web.handler.BaseExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Piotr on 13.04.2017.
 */
@ControllerAdvice
public class ProjectExceptionHandler implements BaseExceptionHandler<ProjectException> {

    @Override
    @ResponseBody
    @ExceptionHandler(value = ProjectException.class)
    public ResponseEntity<ApiResponseError> handleException(ProjectException e) {
        switch (e.getFailReason()) {
            case PROJECT_DOES_NOT_EXIST:
                return returnApiResponseError(e, HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
