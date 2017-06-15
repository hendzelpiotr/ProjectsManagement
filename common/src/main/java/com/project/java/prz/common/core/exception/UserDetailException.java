package com.project.java.prz.common.core.exception;

import lombok.Getter;

/**
 * Created by Piotr on 04.06.2017.
 */
@Getter
public class UserDetailException extends BaseException {

    public UserDetailException(FailReason failReason) {
        this.failReason = failReason;
    }

    private FailReason failReason;

    public enum FailReason {
        USER_DETAIL_DOES_NOT_EXIST,
        USER_DETAIL_ALREADY_EXISTS
    }
}
