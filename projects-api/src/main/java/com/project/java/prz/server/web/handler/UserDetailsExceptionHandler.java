package com.project.java.prz.server.web.handler;

import com.project.java.prz.common.core.exception.UserDetailException;
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
public class UserDetailsExceptionHandler implements BaseExceptionHandler<UserDetailException> {

    @Override
    @ResponseBody
    @ExceptionHandler(value = UserDetailException.class)
    public ResponseEntity<ApiResponseError> handleException(UserDetailException e) {
        switch (e.getFailReason()) {
            case USER_DETAIL_ALREADY_EXISTS:
                return returnApiResponseError(e, HttpStatus.BAD_REQUEST);
            case USER_DETAIL_DOES_NOT_EXIST:
                return returnApiResponseError(e, HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
