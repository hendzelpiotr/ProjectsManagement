package com.project.java.prz.common.core.exception;

import lombok.Getter;

/**
 * Created by Piotr on 13.04.2017.
 */
@Getter
public class UserSettingException extends BaseException {

    public UserSettingException(FailReason failReason) {
        this.failReason = failReason;
    }

    private FailReason failReason;

    public enum FailReason {
        YOU_CAN_NOT_UPDATE_USER_SETTING,
    }

}
