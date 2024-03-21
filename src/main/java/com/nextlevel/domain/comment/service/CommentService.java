package com.nextlevel.domain.comment.service;

import com.nextlevel.domain.comment.dto.request.CommentRequestDto;
import com.nextlevel.domain.comment.dto.response.CommentResponseDto;
import com.nextlevel.domain.comment.entity.Comment;
import com.nextlevel.domain.post.entity.Post;
import com.nextlevel.domain.comment.mapper.CommentMapper;
import com.nextlevel.domain.comment.repository.CommentRepository;
import com.nextlevel.domain.post.repository.PostRepository;
import com.nextlevel.domain.user.dto.SecurityUserDetailsDto;
import com.nextlevel.domain.user.entity.User;
import com.nextlevel.domain.user.repository.UserRepository;
import com.nextlevel.global.codes.ErrorCode;
import com.nextlevel.global.exception.ProfileApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentMapper mapper;

    public void createComment(Long postId, CommentRequestDto commentRequestDto, SecurityUserDetailsDto userPrincipal) {
        User user = userRepository.findById(userPrincipal.getUserDto().userId())
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.USER_NOT_FOUND));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.POST_NOT_FOUND));

        Comment comment = mapper.commentRequestDtoToComment(commentRequestDto);
        comment.mappingUser(user);
        comment.mappingPost(post);

        commentRepository.save(comment);
    }

    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto, SecurityUserDetailsDto userPrincipal) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.COMMENT_NOT_FOUND));

        if (!(userPrincipal.getUserDto().userId() == comment.getUser().getUserId())) {
            throw new ProfileApplicationException(ErrorCode.USER_UNAUTHORIZED);
        }
        comment.update(commentRequestDto);

        return mapper.commentToCommentResponseDto(comment);
    }

    @Transactional(readOnly = true)
    public CommentResponseDto getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.COMMENT_NOT_FOUND));

        return mapper.commentToCommentResponseDto(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComments(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.POST_NOT_FOUND));
        List<Comment> comments = commentRepository.findByPost(post);

        return mapper.commentsToCommentResponseDtos(comments);
    }

    public void deleteComment(Long commentId, SecurityUserDetailsDto userPrincipal) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.COMMENT_NOT_FOUND));

        if (!(userPrincipal.getUserDto().userId() == comment.getUser().getUserId())) {
            throw new ProfileApplicationException(ErrorCode.USER_UNAUTHORIZED);
        }

        commentRepository.delete(comment);
    }
}
