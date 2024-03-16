package com.nextlevel.domain.post.entity;

import com.nextlevel.domain.user.entity.User;
import com.nextlevel.global.audit.BaseTimeEntity;
import com.nextlevel.domain.post.dto.request.CommentReactionRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Getter
@Entity
@Table(name = "comment_reactions")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReaction extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_reaction_id")
    private Long id;

    private ReactionType reactionType;

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

    public void update(CommentReactionRequestDto commentReactionRequestDto) {
        Optional.ofNullable(commentReactionRequestDto.getReactionType()).ifPresent(value -> this.reactionType = value);
    }
}
