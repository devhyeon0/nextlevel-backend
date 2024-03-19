package com.nextlevel.domain.post.service;

import com.nextlevel.domain.post.dto.request.ReportRequestDto;
import com.nextlevel.domain.post.dto.response.ReportResponseDto;
import com.nextlevel.domain.post.entity.Post;
import com.nextlevel.domain.post.entity.PostReport;
import com.nextlevel.domain.post.mapper.ReportMapper;
import com.nextlevel.domain.post.repository.PostReportRepository;
import com.nextlevel.domain.post.repository.PostRepository;
import com.nextlevel.domain.user.dto.SecurityUserDetailsDto;
import com.nextlevel.domain.user.entity.User;
import com.nextlevel.domain.user.repository.UserRepository;
import com.nextlevel.global.codes.ErrorCode;
import com.nextlevel.global.exception.ProfileApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

    private final PostReportRepository postReportRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReportMapper mapper;

    public void createPostReport(Long postId, ReportRequestDto reportRequestDto, SecurityUserDetailsDto userPrincipal) {
        User user = userRepository.findById(userPrincipal.userId())
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.USER_NOT_FOUND));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.POST_NOT_FOUND));

        PostReport postReport = mapper.reportDtoToPostReport(reportRequestDto);
        postReport.mappingUser(user);
        postReport.mappingPost(post);

        postReportRepository.save(postReport);
    }

    @Transactional(readOnly = true)
    public ReportResponseDto findPostReport(Long reportId) {
        PostReport postReport = postReportRepository.findById(reportId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.REPORT_NOT_FOUND));

        return mapper.postReportToPostResponseDto(postReport);
    }

    @Transactional(readOnly = true)
    public List<ReportResponseDto> findPostReports(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.POST_NOT_FOUND));
        List<PostReport> postReports = postReportRepository.findByPost(post);

        return mapper.postReportsToPostReportResponseDtos(postReports);
    }
}
