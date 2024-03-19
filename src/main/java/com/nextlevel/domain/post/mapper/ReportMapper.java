package com.nextlevel.domain.post.mapper;

import com.nextlevel.domain.post.dto.request.ReportRequestDto;
import com.nextlevel.domain.post.dto.response.ReportResponseDto;
import com.nextlevel.domain.post.entity.PostReport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    PostReport reportDtoToPostReport(ReportRequestDto reportRequestDto);

    ReportResponseDto postReportToPostResponseDto(PostReport postReport);

    List<ReportResponseDto> postReportsToPostReportResponseDtos(List<PostReport> postReports);
}
