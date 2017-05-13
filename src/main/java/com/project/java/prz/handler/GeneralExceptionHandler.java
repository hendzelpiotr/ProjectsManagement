package com.project.java.prz.handler;

import com.project.java.prz.exception.GeneralException;
import com.project.java.prz.exception.pattern.ApiResponseError;
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
            case IO_EXCEPTION:
                return returnApiResponseError(e, HttpStatus.INTERNAL_SERVER_ERROR);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
