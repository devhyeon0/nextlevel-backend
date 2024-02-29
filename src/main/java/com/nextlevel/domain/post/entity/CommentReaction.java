package com.nextlevel.domain.post.entity;

import com.nextlevel.global.common.audit.BaseTimeEntity;
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

    public void update(CommentReactionRequestDto commentReactionRequestDto) {
        Optional.ofNullable(commentReactionRequestDto.getReactionType()).ifPresent(value -> this.reactionType = value);
    }
}
