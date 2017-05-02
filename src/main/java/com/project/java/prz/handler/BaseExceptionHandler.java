package com.project.java.prz.handler;

import com.project.java.prz.exception.BaseException;
import com.project.java.prz.exception.pattern.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Piotr on 02.05.2017.
 */
public abstract class BaseExceptionHandler {

    ResponseEntity<ApiResponseError> returnApiResponseError(BaseException exception, HttpStatus httpStatus) {
        ApiResponseError responseError = new ApiResponseError(httpStatus.toString(), exception.getFailReason().toString());
        return new ResponseEntity<>(responseError, httpStatus);
    }

}
