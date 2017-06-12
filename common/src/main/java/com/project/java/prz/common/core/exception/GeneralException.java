package com.project.java.prz.common.core.exception;

import lombok.Getter;

/**
 * Created by Piotr on 13.05.2017.
 */
@Getter
public class GeneralException extends BaseException {

    public GeneralException(FailReason failReason) {
        this.failReason = failReason;
    }

    private FailReason failReason;

    public enum FailReason {
        IO_EXCEPTION,
        INVALID_IDS, RESOURCE_NOT_FOUND
    }

}
