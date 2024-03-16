package com.nextlevel.domain.post.dto.response;

import com.nextlevel.domain.post.entity.ReactionType;
import com.nextlevel.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostReactionResponseDto {

    private Long postReactionId;
    private ReactionType reactionType;
    private PostResponseDto post;
    private UserResponseDto user;
}
