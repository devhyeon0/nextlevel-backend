package com.nextlevel.domain.post.dto.request;

import com.nextlevel.domain.post.dto.response.CommentResponseDto;
import com.nextlevel.domain.post.entity.ReactionType;
import com.nextlevel.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentReactionRequestDto {

    private ReactionType reactionType;
    private CommentResponseDto comment;
    private UserResponseDto user;
}
