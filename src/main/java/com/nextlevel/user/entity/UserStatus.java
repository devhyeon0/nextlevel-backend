package com.nextlevel.user.entity;

import lombok.Getter;

@Getter
public enum UserStatus {

    ACTIVE("활동중"),
    INACTIVE("휴면 계정");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }
}
