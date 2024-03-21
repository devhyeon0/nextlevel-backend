package com.nextlevel.domain.post.dto.response;

import com.nextlevel.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostReactionResponseDto {

    private Long postReactionId;
    private boolean reactionStatus;
    private PostResponseDto post;
    private UserResponseDto user;
}
