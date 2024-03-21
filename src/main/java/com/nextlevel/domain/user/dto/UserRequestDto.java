package com.nextlevel.domain.user.dto;

import com.nextlevel.domain.user.entity.LoginProvider;
import com.nextlevel.domain.user.entity.UserRole;
import com.nextlevel.domain.user.entity.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W*)(?=\\S+$).{8,15}", message = "비밀번호는 8 ~ 15자 영문 대 소문자, 숫자를 사용하세요.")
    private String password;

    @Pattern(regexp = "^(?!.*[ㄱ-ㅎ])[ㄱ-ㅎ가-힣a-z0-9-_]{2,15}$", message = "닉네임은 특수문자를 제외한 2~15자리여야 합니다.")
    private String nickname;

    private LoginProvider provider;
    private UserRole userRole;
    private UserStatus status;
}
