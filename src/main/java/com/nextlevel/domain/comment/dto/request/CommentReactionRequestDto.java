package com.nextlevel.domain.comment.dto.request;

import com.nextlevel.domain.comment.dto.response.CommentResponseDto;
import com.nextlevel.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentReactionRequestDto {

    private boolean reactionStatus;
    private CommentResponseDto comment;
    private UserResponseDto user;
}
