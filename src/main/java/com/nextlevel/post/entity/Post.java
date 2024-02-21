package com.nextlevel.post.entity;

import com.nextlevel.common.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.*;

@Getter
@Entity
@Table(name = "posts")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    private String content;

    @Column(nullable = false)
    private Long views;

    @Enumerated(STRING)
    @Column(nullable = false)
    private PostStatus status;

    @Column(nullable = false)
    private Integer notify;

    @Column(nullable = false)
    private Integer createIp;
}
