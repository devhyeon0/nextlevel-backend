package com.nextlevel.domain.post.entity;

import com.nextlevel.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "comment_report")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @Enumerated(EnumType.STRING)
    private ReportReason reportReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commnet_id")
    private Comment comment;

    public void mappingUser(User user) {
        this.user = user;
        user.mappingCommentReport(this);
    }

    public void mappingComment(Comment comment) {
        this.comment = comment;
        comment.mappingCommentReport(this);
    }
}
