package com.nextlevel.domain.post.service;

import com.nextlevel.domain.post.dto.request.CommentRequestDto;
import com.nextlevel.domain.post.dto.response.CommentResponseDto;
import com.nextlevel.domain.post.entity.Comment;
import com.nextlevel.domain.post.mapper.CommentMapper;
import com.nextlevel.domain.post.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public CommentResponseDto getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        return mapper.commentToCommentResponseDto(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComments() {
        List<Comment> comments = commentRepository.findAll();

        return mapper.commentsToCommentResponseDtos(comments);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
