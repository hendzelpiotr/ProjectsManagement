package com.project.java.prz.web.handler;

import com.project.java.prz.core.exception.BaseException;
import com.project.java.prz.core.exception.pattern.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Piotr on 02.05.2017.
 */
public interface BaseExceptionHandler<E extends BaseException> {

    ResponseEntity<ApiResponseError> handleException(E e);

    default ResponseEntity<ApiResponseError> returnApiResponseError(E exception, HttpStatus httpStatus) {
        ApiResponseError responseError = new ApiResponseError(httpStatus.toString(), exception.getFailReason().toString());
        return new ResponseEntity<>(responseError, httpStatus);
    }

}
