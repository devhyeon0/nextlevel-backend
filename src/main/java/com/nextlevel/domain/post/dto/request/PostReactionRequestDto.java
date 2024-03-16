package com.nextlevel.domain.post.dto.request;

import com.nextlevel.domain.post.dto.response.PostResponseDto;
import com.nextlevel.domain.post.entity.ReactionType;
import com.nextlevel.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostReactionRequestDto {

    private ReactionType reactionType;
    private PostResponseDto post;
    private UserResponseDto user;
}
