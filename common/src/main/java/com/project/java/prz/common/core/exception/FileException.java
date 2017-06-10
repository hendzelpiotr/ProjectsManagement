package com.project.java.prz.common.core.exception;

import lombok.Getter;

/**
 * Created by Piotr on 10.06.2017.
 */
@Getter
public class FileException extends BaseException {

    public FileException(FailReason failReason) {
        this.failReason = failReason;
    }

    private FailReason failReason;

    public enum FailReason {
        YOU_CAN_NOT_SAVE_FILE
    }


}
