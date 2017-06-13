package com.project.java.prz.server.web.handler;

import com.project.java.prz.common.core.exception.UserSettingException;
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
public class UserSettingExceptionHandler implements BaseExceptionHandler<UserSettingException> {

    @Override
    @ResponseBody
    @ExceptionHandler(value = UserSettingException.class)
    public ResponseEntity<ApiResponseError> handleException(UserSettingException e) {
        switch (e.getFailReason()) {
            case YOU_CAN_NOT_UPDATE_USER_SETTING:
                return returnApiResponseError(e, HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
