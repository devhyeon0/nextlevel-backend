package com.nextlevel.domain.comment.dto.response;

import com.nextlevel.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentReactionResponseDto {

    private Long id;
    private boolean reactionStatus;
    private CommentResponseDto comment;
    private UserResponseDto user;
}
