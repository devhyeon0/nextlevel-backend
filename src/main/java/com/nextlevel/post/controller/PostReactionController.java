package com.nextlevel.post.controller;

import com.nextlevel.post.dto.PostReactionRequestDto;
import com.nextlevel.post.service.PostReactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
