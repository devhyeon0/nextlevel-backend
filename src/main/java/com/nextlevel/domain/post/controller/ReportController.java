package com.nextlevel.domain.post.controller;

import com.nextlevel.domain.comment.dto.request.CommentReportRequestDto;
import com.nextlevel.domain.post.dto.request.PostReportRequestDto;
import com.nextlevel.domain.comment.dto.response.CommentReportResponseDto;
import com.nextlevel.domain.post.dto.response.PostReportResponseDto;
import com.nextlevel.domain.post.service.ReportService;
import com.nextlevel.domain.user.dto.SecurityUserDetailsDto;
import com.nextlevel.global.dto.MultiResponseDto;
import com.nextlevel.global.dto.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/post/{id}/report")
    public ResponseEntity<Objects> createPostReport(@PathVariable("id") Long postId,
                                                    @RequestBody PostReportRequestDto postReportRequestDto,
                                                    @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        reportService.createPostReport(postId, postReportRequestDto, userPrincipal);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/post/{id}/report/{reportId}")
    public ResponseEntity<SingleResponseDto> getPostReport(@PathVariable("id") Long postId,
                                                           @PathVariable("reportId") Long reportId) {
        PostReportResponseDto reportResponseDto = reportService.findPostReport(reportId);

        return ResponseEntity.ok(new SingleResponseDto<>(reportResponseDto));
    }

    @GetMapping("/post/{id}/report")
    public ResponseEntity<MultiResponseDto> getPostReports(@PathVariable("id") Long postId) {
        List<PostReportResponseDto> reportResponseDtos = reportService.findPostReports(postId);

        return ResponseEntity.ok(new MultiResponseDto<>(reportResponseDtos));
    }

    @PostMapping("/comment/{id}/report")
    public ResponseEntity<Objects> createCommentReport(@PathVariable("id") Long commentId,
                                                       @RequestBody CommentReportRequestDto commentReportRequestDto,
                                                       @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        reportService.createCommentReport(commentId, commentReportRequestDto, userPrincipal);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/comment/{id}/report/{reportId}")
    public ResponseEntity<SingleResponseDto> getCommentReport(@PathVariable("id") Long commentId,
                                                              @PathVariable("reportId") Long reportId) {
        CommentReportResponseDto reportResponseDto = reportService.findCommentReport(reportId);

        return ResponseEntity.ok(new SingleResponseDto<>(reportResponseDto));
    }

    @GetMapping("/comment/{id}/report")
    public ResponseEntity<MultiResponseDto> getCommentReports(@PathVariable("id") Long commentId) {
        List<CommentReportResponseDto> reportResponseDtos = reportService.findCommentReports(commentId);

        return ResponseEntity.ok(new MultiResponseDto<>(reportResponseDtos));
    }
}
