package com.nextlevel.global.exception;

import lombok.Getter;

@Getter
public class ProfileApplicationException extends RuntimeException {

    private final ErrorCode errorCode;

    public ProfileApplicationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
