package com.nextlevel.domain.post.controller;

import com.nextlevel.domain.post.dto.request.PostRequestDto;
import com.nextlevel.domain.post.dto.response.PostResponseDto;
import com.nextlevel.domain.post.service.PostService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Objects> createPost(@Valid @RequestBody PostRequestDto postRequestDto,
                                              Principal principal) {
        postService.createPost(postRequestDto, principal);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostResponseDto> patchPost(@PathVariable("id") Long postId,
                                                     @Valid @RequestBody PostRequestDto postRequestDto) {
        PostResponseDto postResponseDto = postService.updatePost(postId, postRequestDto);

        return ResponseEntity.ok(postResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable("id") Long postId,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response) {
        PostResponseDto postResponseDto = postService.findPost(postId);
        addViewCount(postId, request, response);
        return ResponseEntity.ok(postResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        List<PostResponseDto> postResponseDtos = postService.findPosts();

        return ResponseEntity.ok(postResponseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> deletePost(@PathVariable("id") Long postId) {
        postService.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void addViewCount(Long postId, HttpServletRequest request, HttpServletResponse response) {
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("postView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + postId.toString() + "]")) {
                postService.addViewCount(postId);
                oldCookie.setValue(oldCookie.getValue() + "_[" + postId + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            postService.addViewCount(postId);
            Cookie newCookie = new Cookie("postView", "[" + postId + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }
    }
}
