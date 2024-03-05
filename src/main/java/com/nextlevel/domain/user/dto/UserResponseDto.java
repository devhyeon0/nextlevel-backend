package com.nextlevel.domain.user.dto;

import com.nextlevel.domain.user.entity.UserRole;
import com.nextlevel.domain.user.entity.UserStatus;
import com.nextlevel.domain.user.entity.LoginProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String email;
    private String password;
    private String nickname;
    private LoginProvider provider;
    private UserRole userRole;
    private UserStatus status;
}
