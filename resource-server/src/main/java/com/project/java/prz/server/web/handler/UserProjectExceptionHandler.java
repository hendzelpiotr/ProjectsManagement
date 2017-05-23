package com.project.java.prz.server.web.handler;

import com.project.java.prz.common.core.exception.UserProjectException;
import com.project.java.prz.common.core.exception.pattern.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Piotr on 13.04.2017.
 */
@ControllerAdvice
public class UserProjectExceptionHandler implements BaseExceptionHandler<UserProjectException> {

    @Override
    @ResponseBody
    @ExceptionHandler(value = UserProjectException.class)
    public ResponseEntity<ApiResponseError> handleException(UserProjectException e) {
        switch (e.getFailReason()) {
            case YOU_ALREADY_CHOSE_PROJECT:
            case YOU_CAN_NOT_ABANDON_PROJECT:
            case YOU_CAN_NOT_UPDATE_USER_PROJECT:
            case INVALID_IDS:
                return returnApiResponseError(e, HttpStatus.BAD_REQUEST);
            case USER_PROJECT_NOT_FOUND:
                return returnApiResponseError(e, HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
