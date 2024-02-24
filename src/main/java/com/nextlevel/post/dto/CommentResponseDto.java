package com.nextlevel.post.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponseDto {

    public Long commentId;
    public String content;
    public Integer reportCount;
}
