package com.nextlevel.domain.post.controller;

import com.nextlevel.domain.post.dto.request.PostReactionRequestDto;
import com.nextlevel.domain.post.service.PostReactionService;
import com.nextlevel.domain.post.dto.response.PostReactionResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PostReactionResponseDto> patchReaction(@PathVariable("id") Long postReactionId,
                                                                 @Valid @RequestBody PostReactionRequestDto postReactionRequestDto) {
        PostReactionResponseDto postReactionResponseDto = postReactionService.updateReaction(postReactionId, postReactionRequestDto);

        return ResponseEntity.ok(postReactionResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostReactionResponseDto> getReaction(@PathVariable("id") Long postReactionId) {
        PostReactionResponseDto postReactionResponseDto = postReactionService.findPostReaction(postReactionId);

        return ResponseEntity.ok(postReactionResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<PostReactionResponseDto>> getAllReaction() {
        List<PostReactionResponseDto> postReactionResponseDtos = postReactionService.findAllPostReaction();

        return ResponseEntity.ok(postReactionResponseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> deleteReaction(@PathVariable("id") Long postReactionId) {
        postReactionService.deleteReaction(postReactionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
