package com.project.java.prz.common.core.exception;

import lombok.Getter;

/**
 * Created by phendzel on 5/24/2017.
 */
@Getter
public class UserException extends BaseException {

    public UserException(FailReason failReason) {
        this.failReason = failReason;
    }

    private FailReason failReason;

    public enum FailReason {
        USER_NOT_FOUND,
        USER_ALREADY_ENABLED, INVALID_IDS, CAN_NOT_CREATE_USER, USER_ALREADY_EXITS
    }

}
