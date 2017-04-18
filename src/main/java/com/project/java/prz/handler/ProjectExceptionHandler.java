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
            case YOU_CAN_NOT_ABANDON_PROJECT:
                return returnApiResponseError(projectException, HttpStatus.BAD_REQUEST);
            case PROJECT_DOES_NOT_EXIST:
                return returnApiResponseError(projectException, HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<ApiResponseError> returnApiResponseError(ProjectException projectException, HttpStatus httpStatus) {
        ApiResponseError responseError = new ApiResponseError(httpStatus.toString(), projectException.getFailReason().toString());
        return new ResponseEntity<>(responseError, httpStatus);
    }

}
