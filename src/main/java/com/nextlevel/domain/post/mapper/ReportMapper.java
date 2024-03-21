package com.nextlevel.domain.post.mapper;

import com.nextlevel.domain.comment.dto.request.CommentReportRequestDto;
import com.nextlevel.domain.post.dto.request.PostReportRequestDto;
import com.nextlevel.domain.comment.dto.response.CommentReportResponseDto;
import com.nextlevel.domain.post.dto.response.PostReportResponseDto;
import com.nextlevel.domain.comment.entity.CommentReport;
import com.nextlevel.domain.post.entity.PostReport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    PostReport postReportRequestDtoToPostReport(PostReportRequestDto postReportRequestDto);

    PostReportResponseDto postReportToPostReportResponseDto(PostReport postReport);

    List<PostReportResponseDto> postReportsToPostReportResponseDtos(List<PostReport> postReports);

    CommentReport commentReportRequestDtoToCommentReport(CommentReportRequestDto commentReportRequestDto);

    CommentReportResponseDto commentReportToCommentReportResponseDto(CommentReport commentReport);

    List<CommentReportResponseDto> commentReportsToCommentReportResponseDtos(List<CommentReport> commentReports);
}
