package com.nextlevel.domain.post.dto.response;

import com.nextlevel.domain.post.entity.ReactionType;
import com.nextlevel.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentReactionResponseDto {

    private Long id;
    private ReactionType reactionType;
    private CommentResponseDto comment;
    private UserResponseDto user;
}
