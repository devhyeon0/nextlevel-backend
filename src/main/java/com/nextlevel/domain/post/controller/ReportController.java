package com.nextlevel.domain.post.controller;

import com.nextlevel.domain.post.dto.request.ReportRequestDto;
import com.nextlevel.domain.post.dto.response.ReportResponseDto;
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
                                                    @RequestBody ReportRequestDto reportRequestDto,
                                                    @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        reportService.createPostReport(postId, reportRequestDto, userPrincipal);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/post/{id}/report/{reportId}")
    public ResponseEntity<SingleResponseDto> getPostReport(@PathVariable("id") Long postId,
                                                           @PathVariable("reportId") Long reportId) {
        ReportResponseDto reportResponseDto = reportService.findPostReport(reportId);

        return ResponseEntity.ok(new SingleResponseDto<>(reportResponseDto));
    }

    @GetMapping("/post/{id}/report")
    public ResponseEntity<MultiResponseDto> getPostReports(@PathVariable("id") Long postId) {
        List<ReportResponseDto> reportResponseDtos = reportService.findPostReports(postId);

        return ResponseEntity.ok(new MultiResponseDto<>(reportResponseDtos));
    }
}
