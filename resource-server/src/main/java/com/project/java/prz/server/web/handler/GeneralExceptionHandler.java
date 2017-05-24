package com.project.java.prz.server.web.handler;

import com.project.java.prz.common.core.exception.GeneralException;
import com.project.java.prz.common.core.exception.pattern.ApiResponseError;
import com.project.java.prz.common.web.handler.BaseExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Piotr on 13.05.2017.
 */
@ControllerAdvice
public class GeneralExceptionHandler implements BaseExceptionHandler<GeneralException> {

    @Override
    @ResponseBody
    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<ApiResponseError> handleException(GeneralException e) {
        switch (e.getFailReason()) {
            case RESOURCE_NOT_FOUND:
                return returnApiResponseError(e, HttpStatus.NOT_FOUND);
            case IO_EXCEPTION:
                return returnApiResponseError(e, HttpStatus.INTERNAL_SERVER_ERROR);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
