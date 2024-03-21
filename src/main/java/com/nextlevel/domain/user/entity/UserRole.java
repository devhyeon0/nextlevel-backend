package com.nextlevel.domain.user.entity;

import lombok.Getter;

@Getter
public enum UserRole {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String status;

    UserRole(String status) {
        this.status = status;
    }
}
