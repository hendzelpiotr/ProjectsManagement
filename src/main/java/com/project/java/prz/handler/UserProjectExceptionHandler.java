package com.project.java.prz.handler;

import com.project.java.prz.exception.UserProjectException;
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
public class UserProjectExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(value = UserProjectException.class)
    @ResponseBody
    public ResponseEntity<ApiResponseError> projectException(UserProjectException userProjectException) {
        switch (userProjectException.getFailReason()) {
            case YOU_ALREADY_CHOSE_PROJECT:
            case YOU_CAN_NOT_ABANDON_PROJECT:
            case YOU_CAN_NOT_UPDATE_USER_PROJECT:
            case INVALID_IDS:
                return returnApiResponseError(userProjectException, HttpStatus.BAD_REQUEST);
            case USER_PROJECT_NOT_FOUND:
                return returnApiResponseError(userProjectException, HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
