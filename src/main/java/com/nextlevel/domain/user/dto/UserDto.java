package com.nextlevel.domain.user.dto;

import com.nextlevel.domain.user.entity.LoginProvider;
import com.nextlevel.domain.user.entity.UserRole;
import com.nextlevel.domain.user.entity.UserStatus;

import java.time.LocalDateTime;

public record UserDto(Long userId,
                      String email,
                      String password,
                      String nickname,
                      LoginProvider provider,
                      UserRole userRole,
                      UserStatus status,
                      LocalDateTime createdAt,
                      LocalDateTime modifiedAt) {
}
