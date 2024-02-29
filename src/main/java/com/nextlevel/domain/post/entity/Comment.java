package com.nextlevel.domain.post.entity;

import com.nextlevel.global.common.audit.BaseEntity;
import com.nextlevel.domain.post.dto.request.CommentRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Getter
@Entity
@Builder
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commnet_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer reportCount;

    public void update(CommentRequestDto commentDto) {
        Optional.ofNullable(commentDto.getContent()).ifPresent(value -> this.content = value);
        Optional.ofNullable(commentDto.getReportCount()).ifPresent(value -> this.reportCount = value);
    }
}
