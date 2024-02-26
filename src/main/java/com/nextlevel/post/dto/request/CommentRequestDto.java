package com.nextlevel.post.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentRequestDto {

    @NotNull
    private String content;

    private Integer reportCount;
}
