package com.nextlevel.domain.post.controller;

import com.nextlevel.domain.post.dto.request.PostReactionRequestDto;
import com.nextlevel.domain.post.service.PostReactionService;
import com.nextlevel.domain.post.dto.response.PostReactionResponseDto;
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
@RequestMapping("/api/post-reaction")
@RequiredArgsConstructor
public class PostReactionController {

    private final PostReactionService postReactionService;

    @PostMapping
    public ResponseEntity<Objects> createReaction(@Valid @RequestBody PostReactionRequestDto postReactionRequestDto) {
        postReactionService.createReaction(postReactionRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SingleResponseDto> patchReaction(@PathVariable("id") Long postReactionId,
                                                           @Valid @RequestBody PostReactionRequestDto postReactionRequestDto,
                                                           @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        PostReactionResponseDto postReactionResponseDto = postReactionService.updateReaction(postReactionId, postReactionRequestDto, userPrincipal);

        return ResponseEntity.ok(new SingleResponseDto<>(postReactionResponseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleResponseDto> getReaction(@PathVariable("id") Long postReactionId) {
        PostReactionResponseDto postReactionResponseDto = postReactionService.findPostReaction(postReactionId);

        return ResponseEntity.ok(new SingleResponseDto<>(postReactionResponseDto));
    }

    @GetMapping
    public ResponseEntity<MultiResponseDto> getAllReaction() {
        List<PostReactionResponseDto> postReactionResponseDtos = postReactionService.findAllPostReaction();

        return ResponseEntity.ok(new MultiResponseDto<>(postReactionResponseDtos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> deleteReaction(@PathVariable("id") Long postReactionId,
                                                  @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        postReactionService.deleteReaction(postReactionId, userPrincipal);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
