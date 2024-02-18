package com.nextlevel.user.entity;

import com.nextlevel.common.audit.BaseTimeEntity;
import com.nextlevel.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

import static jakarta.persistence.EnumType.*;

@Getter
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
    private String nickname;

    @Enumerated(STRING)
    @Column(nullable = false)
    private LoginProvider provider;

    @Enumerated(STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @Enumerated(STRING)
    @Column(nullable = false)
    private UserStatus status;

    public void update(UserRequestDto userDto) {
        Optional.ofNullable(userDto.getNickname()).ifPresent(value -> this.nickname = value);
        Optional.ofNullable(userDto.getUserRole()).ifPresent(value -> this.userRole = value);
        Optional.ofNullable(userDto.getStatus()).ifPresent(value -> this.status = value);
    }
}
