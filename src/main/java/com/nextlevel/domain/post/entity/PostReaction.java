package com.nextlevel.domain.post.entity;

import com.nextlevel.domain.post.dto.request.PostReactionRequestDto;
import com.nextlevel.domain.user.entity.User;
import com.nextlevel.global.audit.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Getter
@Entity
@Table(name = "post_reaction")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReaction extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postReactionId;

    private ReactionType reactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void mappingPost(Post post) {
        this.post = post;
        post.mappingPostReaction(this);
    }

    public void mappingUser(User user) {
        this.user = user;
        user.mappingPostReaction(this);
    }

    public void update(PostReactionRequestDto postReactionRequestDto) {
        Optional.ofNullable(postReactionRequestDto.getReactionType()).ifPresent(value -> this.reactionType = value);
    }
}
