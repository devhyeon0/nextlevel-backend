package com.nextlevel.post.controller;

import com.nextlevel.post.dto.PostRequestDto;
import com.nextlevel.post.dto.PostResponseDto;
import com.nextlevel.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{id}")
    public ResponseEntity<PostResponseDto> patchPost(@PathVariable("id") Long postId,
                                                     @Valid @RequestBody PostRequestDto postRequestDto) {
        PostResponseDto postResponseDto = postService.updatePost(postId, postRequestDto);

        return ResponseEntity.ok(postResponseDto);
    }
}
