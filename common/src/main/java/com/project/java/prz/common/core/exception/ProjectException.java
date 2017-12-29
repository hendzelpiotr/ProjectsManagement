package com.project.java.prz.common.core.exception;

import lombok.Getter;

/**
 * Created by Piotr on 13.04.2017.
 */
@Getter
public class ProjectException extends BaseException {

    public ProjectException(FailReason failReason) {
        this.failReason = failReason;
    }

    private FailReason failReason;

    public enum FailReason {
        PROJECT_DOES_NOT_EXIST,
        PROJECT_CAN_NOT_BE_REMOVED
    }

}
