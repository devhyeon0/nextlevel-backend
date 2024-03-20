package com.nextlevel.domain.post.dto.request;

import com.nextlevel.domain.post.dto.response.CommentResponseDto;
import com.nextlevel.domain.post.entity.ReportReason;
import com.nextlevel.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentReportRequestDto {

    private ReportReason reportReason;
    private UserResponseDto user;
    private CommentResponseDto comment;
}
