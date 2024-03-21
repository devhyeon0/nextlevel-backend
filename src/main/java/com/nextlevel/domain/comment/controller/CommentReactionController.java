package com.nextlevel.domain.comment.controller;

import com.nextlevel.domain.comment.dto.request.CommentReactionRequestDto;
import com.nextlevel.domain.comment.dto.response.CommentReactionResponseDto;
import com.nextlevel.domain.comment.service.CommentReactionService;
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
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentReactionController {

    private final CommentReactionService commentReactionService;

    @PostMapping("/{id}/reaction")
    public ResponseEntity<Objects> createReaction(@PathVariable("id") Long commentId,
                                                  @Valid @RequestBody CommentReactionRequestDto commentReactionRequestDto,
                                                  @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        commentReactionService.createReaction(commentId, commentReactionRequestDto, userPrincipal);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}/reaction/{reactionId}")
    public ResponseEntity<SingleResponseDto> getReaction(@PathVariable("id") Long commentId,
                                                         @PathVariable("reactionId") Long commentReactionId) {
        CommentReactionResponseDto commentReactionResponseDto = commentReactionService.findCommentReaction(commentReactionId);

        return ResponseEntity.ok(new SingleResponseDto<>(commentReactionResponseDto));
    }

    @GetMapping("/{id}/reaction")
    public ResponseEntity<MultiResponseDto> getAllReaction(@PathVariable("id") Long commentId) {
        List<CommentReactionResponseDto> commentReactionResponseDto = commentReactionService.findAllCommentReaction(commentId);

        return ResponseEntity.ok(new MultiResponseDto<>(commentReactionResponseDto));
    }
}
