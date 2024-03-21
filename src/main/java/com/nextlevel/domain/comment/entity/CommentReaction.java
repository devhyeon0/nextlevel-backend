package com.nextlevel.domain.comment.entity;

import com.nextlevel.domain.user.entity.User;
import com.nextlevel.global.audit.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "comment_reaction")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReaction extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentReactionId;

    @Column(nullable = false)
    private boolean reactionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void mappingComment(Comment comment) {
        this.comment = comment;
        comment.mappingCommentReaction(this);
    }

    public void mappingUser(User user) {
        this.user = user;
        user.mappingCommentReaction(this);
    }
}
