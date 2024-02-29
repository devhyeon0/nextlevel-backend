package com.nextlevel.domain.post.entity;

import com.nextlevel.global.common.audit.BaseEntity;
import com.nextlevel.domain.post.dto.request.PostRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

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
    private Integer reportCount;

    @Column(nullable = false)
    private Integer createIp;

    public void update(PostRequestDto postDto) {
        Optional.ofNullable(postDto.getTitle()).ifPresent(value -> this.title = value);
        Optional.ofNullable(postDto.getContent()).ifPresent(value -> this.content = value);
        Optional.ofNullable(postDto.getViews()).ifPresent(value -> this.views = value);
        Optional.ofNullable(postDto.getStatus()).ifPresent(value -> this.status = value);
        Optional.ofNullable(postDto.getReportCount()).ifPresent(value -> this.reportCount = value);
    }
}
