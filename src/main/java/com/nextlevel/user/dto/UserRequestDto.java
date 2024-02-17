package com.nextlevel.user.dto;

import com.nextlevel.user.entity.LoginProvider;
import com.nextlevel.user.entity.UserRole;
import com.nextlevel.user.entity.UserStatus;
import lombok.*;

@Getter
@Builder
public class UserRequestDto {

    private String email;
    private String nickname;
    private LoginProvider provider;
    private UserRole userRole;
    private UserStatus status;
}
