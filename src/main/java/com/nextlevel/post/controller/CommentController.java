package com.nextlevel.post.controller;

import com.nextlevel.post.dto.request.CommentRequestDto;
import com.nextlevel.post.dto.response.CommentResponseDto;
import com.nextlevel.post.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Objects> createComment(@Valid @RequestBody CommentRequestDto commentRequestDto) {
        commentService.createComment(commentRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> patchComment(@PathVariable("id") Long commentId,
                                                           @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.updateComment(commentId, commentRequestDto);

        return ResponseEntity.ok(commentResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable("id") Long commentId) {
        CommentResponseDto commentResponseDto = commentService.getComment(commentId);

        return ResponseEntity.ok(commentResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments() {
        List<CommentResponseDto> commentResponseDtos = commentService.getComments();

        return ResponseEntity.ok(commentResponseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> deleteComment(@PathVariable("id") Long commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
