package com.nextlevel.post.controller;

import com.nextlevel.post.dto.CommentRequestDto;
import com.nextlevel.post.dto.CommentResponseDto;
import com.nextlevel.post.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
