package com.nextlevel.user.dto;

import com.nextlevel.user.entity.LoginProvider;
import com.nextlevel.user.entity.UserRole;
import com.nextlevel.user.entity.UserStatus;
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

    @NotBlank
    private String nickname;

    private LoginProvider provider;
    private UserRole userRole;
    private UserStatus status;
}
