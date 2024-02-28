package com.nextlevel.post.controller;

import com.nextlevel.post.dto.request.CommentReactionRequestDto;
import com.nextlevel.post.dto.response.CommentReactionResponseDto;
import com.nextlevel.post.service.CommentReactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/comment-reaction")
@RequiredArgsConstructor
public class CommentReactionController {

    private final CommentReactionService commentReactionService;

    @PostMapping
    public ResponseEntity<Objects> createReaction(@Valid @RequestBody CommentReactionRequestDto commentReactionRequestDto) {
        commentReactionService.createReaction(commentReactionRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentReactionResponseDto> patchReaction(@PathVariable("id") Long commentReactionId,
                                                                 @Valid @RequestBody CommentReactionRequestDto commentReactionRequestDto) {
        CommentReactionResponseDto commentReactionResponseDto = commentReactionService.updateReaction(commentReactionId, commentReactionRequestDto);

        return ResponseEntity.ok(commentReactionResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentReactionResponseDto> getReaction(@PathVariable("id") Long commentReactionId) {
        CommentReactionResponseDto commentReactionResponseDto = commentReactionService.findCommentReaction(commentReactionId);

        return ResponseEntity.ok(commentReactionResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<CommentReactionResponseDto>> getAllReaction() {
        List<CommentReactionResponseDto> commentReactionResponseDto = commentReactionService.findAllCommentReaction();

        return ResponseEntity.ok(commentReactionResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> deleteReaction(@PathVariable("id") Long commentReactionId) {
        commentReactionService.deleteReaction(commentReactionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
