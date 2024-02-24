package com.nextlevel.post.controller;

import com.nextlevel.post.dto.CommentRequestDto;
import com.nextlevel.post.service.CommentService;
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
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Objects> createComment(@Valid @RequestBody CommentRequestDto commentRequestDto) {
        commentService.createComment(commentRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
