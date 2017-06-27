package com.project.java.prz.server.web.handler;

import com.project.java.prz.common.core.exception.FileException;
import com.project.java.prz.common.core.exception.pattern.ApiResponseError;
import com.project.java.prz.common.web.handler.BaseExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Piotr on 10.06.2017.
 */
@ControllerAdvice
public class FileExceptionHandler implements BaseExceptionHandler<FileException> {

    @Override
    @ResponseBody
    @ExceptionHandler(value = FileException.class)
    public ResponseEntity<ApiResponseError> handleException(FileException e) {
        switch (e.getFailReason()) {
            case YOU_CAN_NOT_SAVE_FILE:
                return returnApiResponseError(e, HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
