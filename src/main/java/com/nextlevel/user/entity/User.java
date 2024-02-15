package com.nextlevel.user.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
    private String nickname;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @Enumerated(STRING)
    @Column(nullable = false)
    private LoginProvider provider;

    @Enumerated(STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @Enumerated(STRING)
    @Column(nullable = false)
    private UserStatus status;
}
