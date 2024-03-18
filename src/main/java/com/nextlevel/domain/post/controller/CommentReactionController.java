package com.nextlevel.domain.post.controller;

import com.nextlevel.domain.post.dto.request.CommentReactionRequestDto;
import com.nextlevel.domain.post.dto.response.CommentReactionResponseDto;
import com.nextlevel.domain.post.service.CommentReactionService;
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
    public ResponseEntity<SingleResponseDto> patchReaction(@PathVariable("id") Long commentReactionId,
                                                           @Valid @RequestBody CommentReactionRequestDto commentReactionRequestDto,
                                                           @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        CommentReactionResponseDto commentReactionResponseDto = commentReactionService.updateReaction(commentReactionId, commentReactionRequestDto, userPrincipal);

        return ResponseEntity.ok(new SingleResponseDto<>(commentReactionResponseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleResponseDto> getReaction(@PathVariable("id") Long commentReactionId) {
        CommentReactionResponseDto commentReactionResponseDto = commentReactionService.findCommentReaction(commentReactionId);

        return ResponseEntity.ok(new SingleResponseDto<>(commentReactionResponseDto));
    }

    @GetMapping
    public ResponseEntity<MultiResponseDto> getAllReaction() {
        List<CommentReactionResponseDto> commentReactionResponseDto = commentReactionService.findAllCommentReaction();

        return ResponseEntity.ok(new MultiResponseDto<>(commentReactionResponseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> deleteReaction(@PathVariable("id") Long commentReactionId,
                                                  @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        commentReactionService.deleteReaction(commentReactionId, userPrincipal);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
