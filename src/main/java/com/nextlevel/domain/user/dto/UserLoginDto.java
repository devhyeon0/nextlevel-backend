package com.nextlevel.domain.user.dto;

import com.nextlevel.domain.user.entity.LoginProvider;
import com.nextlevel.domain.user.entity.UserRole;
import com.nextlevel.domain.user.entity.UserStatus;

public record UserLoginDto(
        Long userId,
        String email,
        String password,
        String nickname,
        LoginProvider provider,
        UserRole userRole,
        UserStatus status) {
}
