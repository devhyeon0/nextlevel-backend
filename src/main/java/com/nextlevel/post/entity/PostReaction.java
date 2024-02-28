package com.nextlevel.post.entity;

import com.nextlevel.common.audit.BaseTimeEntity;
import com.nextlevel.post.dto.request.PostReactionRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Getter
@Entity
@Table(name = "post_reactions")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReaction extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_reaction_id")
    private Long id;

    private ReactionType reactionType;

    public void update(PostReactionRequestDto postReactionRequestDto) {
        Optional.ofNullable(postReactionRequestDto.getReactionType()).ifPresent(value -> this.reactionType = value);
    }
}
