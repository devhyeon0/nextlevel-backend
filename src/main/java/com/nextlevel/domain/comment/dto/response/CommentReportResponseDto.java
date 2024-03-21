package com.nextlevel.domain.comment.dto.response;

import com.nextlevel.domain.post.entity.ReportReason;
import com.nextlevel.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentReportResponseDto {

    private Long reportId;
    private ReportReason reportReason;
    private UserResponseDto user;
    private CommentResponseDto comment;
}
