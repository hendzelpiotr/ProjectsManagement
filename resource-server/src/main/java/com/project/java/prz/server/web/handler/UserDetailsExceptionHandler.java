package com.project.java.prz.server.web.handler;

import com.project.java.prz.common.core.exception.UserDetailsException;
import com.project.java.prz.common.core.exception.pattern.ApiResponseError;
import com.project.java.prz.common.web.handler.BaseExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Piotr on 04.06.2017.
 */
@ControllerAdvice
public class UserDetailsExceptionHandler implements BaseExceptionHandler<UserDetailsException> {

    @Override
    @ResponseBody
    @ExceptionHandler(value = UserDetailsException.class)
    public ResponseEntity<ApiResponseError> handleException(UserDetailsException e) {
        switch (e.getFailReason()) {
            case USER_DETAILS_ALREADY_EXISTS:
                return returnApiResponseError(e, HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
