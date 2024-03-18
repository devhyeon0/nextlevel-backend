package com.nextlevel.domain.post.controller;

import com.nextlevel.domain.post.dto.request.PostRequestDto;
import com.nextlevel.domain.post.dto.response.PostResponseDto;
import com.nextlevel.domain.post.service.PostService;
import com.nextlevel.domain.user.dto.SecurityUserDetailsDto;
import com.nextlevel.domain.user.entity.User;
import com.nextlevel.global.dto.MultiResponseDto;
import com.nextlevel.global.dto.SingleResponseDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
                                              @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        postService.createPost(postRequestDto, userPrincipal);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SingleResponseDto> patchPost(@PathVariable("id") Long postId,
                                                       @Valid @RequestBody PostRequestDto postRequestDto,
                                                       @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        PostResponseDto postResponseDto = postService.updatePost(postId, postRequestDto, userPrincipal);

        return ResponseEntity.ok(new SingleResponseDto<>(postResponseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleResponseDto> getPost(@PathVariable("id") Long postId,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) {
        PostResponseDto postResponseDto = postService.findPost(postId);
        addViewCount(postId, request, response);

        return ResponseEntity.ok(new SingleResponseDto<>(postResponseDto));
    }

    @GetMapping
    public ResponseEntity<MultiResponseDto> getPosts() {
        List<PostResponseDto> postResponseDtos = postService.findPosts();

        return ResponseEntity.ok(new MultiResponseDto<>(postResponseDtos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> deletePost(@PathVariable("id") Long postId,
                                              @AuthenticationPrincipal SecurityUserDetailsDto userPrincipal) {
        postService.deletePost(postId, userPrincipal);

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
