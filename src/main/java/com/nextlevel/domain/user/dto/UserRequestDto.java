package com.nextlevel.domain.user.dto;

import com.nextlevel.domain.user.entity.LoginProvider;
import com.nextlevel.domain.user.entity.UserRole;
import com.nextlevel.domain.user.entity.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @Email
    private String email;

    private String password;

    @NotBlank
    private String nickname;

    private LoginProvider provider;
    private UserRole userRole;
    private UserStatus status;
}