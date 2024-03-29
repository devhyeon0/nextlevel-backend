package com.nextlevel.domain.post.service;

import com.nextlevel.domain.comment.dto.request.CommentReportRequestDto;
import com.nextlevel.domain.post.dto.request.PostReportRequestDto;
import com.nextlevel.domain.comment.dto.response.CommentReportResponseDto;
import com.nextlevel.domain.post.dto.response.PostReportResponseDto;
import com.nextlevel.domain.comment.entity.Comment;
import com.nextlevel.domain.comment.entity.CommentReport;
import com.nextlevel.domain.post.entity.Post;
import com.nextlevel.domain.post.entity.PostReport;
import com.nextlevel.domain.post.mapper.ReportMapper;
import com.nextlevel.domain.comment.repository.CommentReportRepository;
import com.nextlevel.domain.comment.repository.CommentRepository;
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
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

    private final PostReportRepository postReportRepository;
    private final CommentReportRepository commentReportRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ReportMapper mapper;

    /**
     *  --- post report---
     */

    public void createPostReport(Long postId, PostReportRequestDto postReportRequestDto, SecurityUserDetailsDto userPrincipal) {
        User user = userRepository.findById(userPrincipal.userId())
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.USER_NOT_FOUND));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.POST_NOT_FOUND));

        Optional<PostReport> report = postReportRepository.findByUserAndPost(user, post);
        if (report.isEmpty()) {
            PostReport postReport = mapper.postReportRequestDtoToPostReport(postReportRequestDto);
            postReport.mappingUser(user);
            postReport.mappingPost(post);
            post.addReportCount();

            postReportRepository.save(postReport);
        } else {
            throw new ProfileApplicationException(ErrorCode.ALREADY_REPORTED_POST);
        }
    }

    @Transactional(readOnly = true)
    public PostReportResponseDto findPostReport(Long reportId) {
        PostReport postReport = postReportRepository.findById(reportId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.REPORT_NOT_FOUND));

        return mapper.postReportToPostReportResponseDto(postReport);
    }

    @Transactional(readOnly = true)
    public List<PostReportResponseDto> findPostReports(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.POST_NOT_FOUND));
        List<PostReport> postReports = postReportRepository.findByPost(post);

        return mapper.postReportsToPostReportResponseDtos(postReports);
    }

    /**
     *  --- comment report ---
     */

    public void createCommentReport(Long commentId, CommentReportRequestDto commentReportRequestDto, SecurityUserDetailsDto userPrincipal) {
        User user = userRepository.findById(userPrincipal.userId())
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.USER_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.COMMENT_NOT_FOUND));

        Optional<CommentReport> report = commentReportRepository.findByUserAndComment(user, comment);
        if (report.isEmpty()) {
            CommentReport commentReport = mapper.commentReportRequestDtoToCommentReport(commentReportRequestDto);
            commentReport.mappingUser(user);
            commentReport.mappingComment(comment);
            comment.addReportCount();

            commentReportRepository.save(commentReport);
        } else {
            throw new ProfileApplicationException(ErrorCode.ALREADY_REPORTED_COMMENT);
        }
    }

    @Transactional(readOnly = true)
    public CommentReportResponseDto findCommentReport(Long reportId) {
        CommentReport commentReport = commentReportRepository.findById(reportId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.REPORT_NOT_FOUND));

        return mapper.commentReportToCommentReportResponseDto(commentReport);
    }

    @Transactional(readOnly = true)
    public List<CommentReportResponseDto> findCommentReports(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.COMMENT_NOT_FOUND));
        List<CommentReport> commentReports = commentReportRepository.findByComment(comment);

        return mapper.commentReportsToCommentReportResponseDtos(commentReports);
    }
}
