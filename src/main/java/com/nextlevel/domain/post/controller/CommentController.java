package com.nextlevel.domain.post.controller;

import com.nextlevel.domain.post.dto.request.CommentRequestDto;
import com.nextlevel.domain.post.dto.response.CommentResponseDto;
import com.nextlevel.domain.post.service.CommentService;
import com.nextlevel.domain.user.dto.SecurityUserDetailsDto;
import com.nextlevel.global.dto.MultiResponseDto;
import com.nextlevel.global.dto.SingleResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}/comment")
    public ResponseEntity<Objects> createComment(@PathVariable("id") Long postId,
                                                 @Valid @RequestBody CommentRequestDto commentRequestDto,
                                                 @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        commentService.createComment(postId, commentRequestDto, userPrincipal);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/comment/{commentId}")
    public ResponseEntity<SingleResponseDto> patchComment(@PathVariable("id") Long postId,
                                                          @PathVariable("commentId") Long commentId,
                                                          @RequestBody CommentRequestDto commentRequestDto,
                                                          @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        CommentResponseDto commentResponseDto = commentService.updateComment(commentId, commentRequestDto, userPrincipal);

        return ResponseEntity.ok(new SingleResponseDto<>(commentResponseDto));
    }

    @GetMapping("/{id}/comment/{commentId}")
    public ResponseEntity<SingleResponseDto> getComment(@PathVariable("id") Long postId,
                                                        @PathVariable("commentId") Long commentId) {
        CommentResponseDto commentResponseDto = commentService.getComment(commentId);

        return ResponseEntity.ok(new SingleResponseDto<>(commentResponseDto));
    }

    @GetMapping("/{id}/comment")
    public ResponseEntity<MultiResponseDto> getComments(@PathVariable("id") Long postId) {
        List<CommentResponseDto> commentResponseDtos = commentService.getComments(postId);

        return ResponseEntity.ok(new MultiResponseDto<>(commentResponseDtos));
    }

    @DeleteMapping("/{id}/comment/{commentId}")
    public ResponseEntity<Objects> deleteComment(@PathVariable("id") Long postId,
                                                 @PathVariable("commentId") Long commentId,
                                                 @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        commentService.deleteComment(commentId, userPrincipal);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
