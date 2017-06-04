package com.project.java.prz.common.core.exception;

import lombok.Getter;

/**
 * Created by Piotr on 04.06.2017.
 */
@Getter
public class UserDetailsException extends BaseException {

    public UserDetailsException(FailReason failReason) {
        this.failReason = failReason;
    }

    private FailReason failReason;

    public enum FailReason {
        USER_DETAILS_ALREADY_EXISTS
    }
}
