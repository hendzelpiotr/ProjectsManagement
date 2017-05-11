package com.project.java.prz.exception;

import lombok.Getter;

/**
 * Created by Piotr on 13.04.2017.
 */
@Getter
public class UserProjectException extends BaseException {

    public UserProjectException(FailReason failReason) {
        this.failReason = failReason;
    }

    private FailReason failReason;

    public enum FailReason {
        YOU_ALREADY_CHOSE_PROJECT,
        YOU_CAN_NOT_UPDATE_USER_PROJECT,
        INVALID_IDS,
        USER_PROJECT_NOT_FOUND, YOU_CAN_NOT_ABANDON_PROJECT
    }

}
