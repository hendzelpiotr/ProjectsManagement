package com.project.java.prz.user.web.handler;

import com.project.java.prz.common.core.exception.UserException;
import com.project.java.prz.common.core.exception.pattern.ApiResponseError;
import com.project.java.prz.common.web.handler.BaseExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by phendzel on 5/24/2017.
 */
@ControllerAdvice
public class UserExceptionHandler implements BaseExceptionHandler<UserException> {

    @Override
    @ResponseBody
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ApiResponseError> handleException(UserException e) {
        switch (e.getFailReason()) {
            case USER_NOT_FOUND:
                return returnApiResponseError(e, HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
