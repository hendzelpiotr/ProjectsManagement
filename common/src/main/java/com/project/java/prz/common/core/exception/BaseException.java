package com.project.java.prz.common.core.exception;

import lombok.Getter;

/**
 * Created by Piotr on 02.05.2017.
 */
@Getter
public abstract class BaseException extends RuntimeException {
    Object failReason;
}
