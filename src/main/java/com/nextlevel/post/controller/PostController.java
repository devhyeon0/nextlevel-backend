package com.nextlevel.post.controller;

import com.nextlevel.post.dto.PostRequestDto;
import com.nextlevel.post.dto.PostResponseDto;
import com.nextlevel.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable("id") Long postId) {
        PostResponseDto postResponseDto = postService.findPost(postId);

        return ResponseEntity.ok(postResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        List<PostResponseDto> postResponseDtos = postService.findPosts();

        return ResponseEntity.ok(postResponseDtos);
    }
}
