package com.nextlevel.domain.post.entity;

import com.nextlevel.domain.post.dto.request.CommentRequestDto;
import com.nextlevel.domain.user.entity.User;
import com.nextlevel.global.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
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
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int reportCount;

    public void addReportCount() {
        this.reportCount += 1;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment")
    private List<CommentReaction> commentReactions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "comment")
    private List<CommentReport> commentReports = new ArrayList<>();

    public void mappingPost(Post post) {
        this.post = post;
        post.mappingComment(this);
    }

    public void mappingCommentReaction(CommentReaction commentReaction) {
        commentReactions.add(commentReaction);
    }

    public void mappingUser(User user) {
        this.user = user;
        user.mappingComment(this);
    }

    public void mappingCommentReport(CommentReport commentReport) {
        commentReports.add(commentReport);
    }

    public void update(CommentRequestDto commentDto) {
        Optional.ofNullable(commentDto.getContent()).ifPresent(value -> this.content = value);
    }
}
