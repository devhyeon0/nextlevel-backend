package com.nextlevel.user.entity;

import com.nextlevel.common.audit.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.*;

@Getter
@Entity
@Table(name = "users")
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

    @Builder
    public User(String email, String nickname, LoginProvider provider, UserRole userRole, UserStatus status) {
        this.email = email;
        this.nickname = nickname;
        this.provider = provider;
        this.userRole = userRole;
        this.status = status;
    }
}
