package com.nextlevel.domain.post.dto.request;

import com.nextlevel.domain.post.dto.response.PostResponseDto;
import com.nextlevel.domain.user.dto.UserResponseDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentRequestDto {

    @NotNull
    private String content;

    private Integer reportCount;
    private PostResponseDto post;
    private UserResponseDto user;
}
