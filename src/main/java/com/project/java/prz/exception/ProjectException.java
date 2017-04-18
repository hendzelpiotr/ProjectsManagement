package com.project.java.prz.exception;

import lombok.Getter;

/**
 * Created by Piotr on 13.04.2017.
 */
@Getter
public class ProjectException extends RuntimeException {

    public ProjectException(FailReason failReason) {
        this.failReason = failReason;
    }

    private FailReason failReason;

    public enum FailReason {
        PROJECT_DOES_NOT_EXIST,
        YOU_ALREADY_CHOSE_PROJECT,
        YOU_CAN_NOT_ABANDON_PROJECT
    }

}
