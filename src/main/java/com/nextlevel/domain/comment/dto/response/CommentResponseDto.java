package com.nextlevel.domain.comment.dto.response;

import com.nextlevel.domain.post.dto.response.PostResponseDto;
import com.nextlevel.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponseDto {

    public Long commentId;
    public String content;
    public Integer reportCount;
    private PostResponseDto post;
    private UserResponseDto user;
}
