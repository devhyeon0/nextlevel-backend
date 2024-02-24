package com.nextlevel.post.service;

import com.nextlevel.post.dto.CommentRequestDto;
import com.nextlevel.post.dto.CommentResponseDto;
import com.nextlevel.post.entity.Comment;
import com.nextlevel.post.mapper.CommentMapper;
import com.nextlevel.post.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper mapper;

    public void createComment(CommentRequestDto commentRequestDto) {
        commentRepository.save(mapper.commentRequestDtoToComment(commentRequestDto));
    }

    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        comment.update(commentRequestDto);

        return mapper.commentToCommentResponseDto(comment);
    }
}
