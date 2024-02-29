package com.nextlevel.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponseDto {

    public Long commentId;
    public String content;
    public Integer reportCount;
}
