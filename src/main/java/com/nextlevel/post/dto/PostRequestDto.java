package com.nextlevel.post.dto;

import com.nextlevel.post.entity.PostStatus;
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
    private Integer createIp;
}
