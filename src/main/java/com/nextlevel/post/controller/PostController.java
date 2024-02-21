package com.nextlevel.post.controller;

import com.nextlevel.post.dto.PostRequestDto;
import com.nextlevel.post.service.PostService;
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
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Objects> createPost(@Valid @RequestBody PostRequestDto postRequestDto) {
        postService.createPost(postRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
