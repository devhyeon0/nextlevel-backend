package com.nextlevel.domain.post.dto.request;

import com.nextlevel.domain.post.dto.response.CategoryResponseDto;
import com.nextlevel.domain.post.entity.PostStatus;
import com.nextlevel.domain.user.dto.UserResponseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostRequestDto {

    @NotBlank
    private String title;

    @NotNull
    private String content;
    private Long views;
    private PostStatus status;
    private Integer reportCount;
    private UserResponseDto user;
    private CategoryResponseDto category;
}
