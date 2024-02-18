package com.nextlevel.user.dto;

import com.nextlevel.user.entity.LoginProvider;
import com.nextlevel.user.entity.UserRole;
import com.nextlevel.user.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String email;
    private String nickname;
    private LoginProvider provider;
    private UserRole userRole;
    private UserStatus status;
}
