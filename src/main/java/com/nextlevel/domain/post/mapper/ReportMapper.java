package com.nextlevel.domain.post.mapper;

import com.nextlevel.domain.post.dto.request.ReportRequestDto;
import com.nextlevel.domain.post.dto.response.ReportResponseDto;
import com.nextlevel.domain.post.entity.CommentReport;
import com.nextlevel.domain.post.entity.PostReport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    PostReport postRequestDtoToPostReport(ReportRequestDto reportRequestDto);

    ReportResponseDto postReportToReportResponseDto(PostReport postReport);

    List<ReportResponseDto> postReportsToReportResponseDtos(List<PostReport> postReports);

    CommentReport commentRequestDtoToCommentReport(ReportRequestDto reportRequestDto);

    ReportResponseDto commentReportToReportResponseDto(CommentReport commentReport);

    List<ReportResponseDto> commentReportsToReportResponseDtos(List<CommentReport> commentReports);
}
