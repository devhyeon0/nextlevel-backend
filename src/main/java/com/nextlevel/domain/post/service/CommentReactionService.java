package com.nextlevel.domain.post.service;

import com.nextlevel.domain.post.dto.request.CommentReactionRequestDto;
import com.nextlevel.domain.post.dto.response.CommentReactionResponseDto;
import com.nextlevel.domain.post.entity.Comment;
import com.nextlevel.domain.post.entity.CommentReaction;
import com.nextlevel.domain.post.mapper.CommentReactionMapper;
import com.nextlevel.domain.post.repository.CommentReactionRepository;
import com.nextlevel.domain.post.repository.CommentRepository;
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
public class CommentReactionService {

    private final CommentReactionRepository commentReactionRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentReactionMapper mapper;

    public void createReaction(Long commentId, CommentReactionRequestDto commentReactionRequestDto, SecurityUserDetailsDto userPrincipal) {
        User user = userRepository.findById(userPrincipal.userId())
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.USER_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.COMMENT_NOT_FOUND));

        CommentReaction commentReaction = mapper.commentReactionRequestDtoToCommentReaction(commentReactionRequestDto);
        commentReaction.mappingUser(user);
        commentReaction.mappingComment(comment);

        commentReactionRepository.save(commentReaction);
    }

    public CommentReactionResponseDto updateReaction(Long commentReactionId, CommentReactionRequestDto commentReactionRequestDto, SecurityUserDetailsDto userPrincipal) {
        CommentReaction findCommentReaction = commentReactionRepository.findById(commentReactionId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.REACTION_NOT_FOUND));

        if(!(userPrincipal.getUserDto().userId() == findCommentReaction.getUser().getUserId())) {
            throw new ProfileApplicationException(ErrorCode.USER_UNAUTHORIZED);
        }
        findCommentReaction.update(commentReactionRequestDto);

        return mapper.commentReactionToCommentReactionResponseDto(findCommentReaction);
    }

    @Transactional(readOnly = true)
    public CommentReactionResponseDto findCommentReaction(Long commentReactionId) {
        CommentReaction findCommentReaction = commentReactionRepository.findById(commentReactionId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.REACTION_NOT_FOUND));

        return mapper.commentReactionToCommentReactionResponseDto(findCommentReaction);
    }

    @Transactional(readOnly = true)
    public List<CommentReactionResponseDto> findAllCommentReaction(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.COMMENT_NOT_FOUND));
        List<CommentReaction> allCommentReaction = commentReactionRepository.findByComment(comment);

        return mapper.allCommentReactionToCommentReactionResponseDtos(allCommentReaction);
    }

    public void deleteReaction(Long commentReactionId, SecurityUserDetailsDto userPrincipal) {
        CommentReaction findCommentReaction = commentReactionRepository.findById(commentReactionId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.REACTION_NOT_FOUND));

        if(!(userPrincipal.getUserDto().userId() == findCommentReaction.getUser().getUserId())) {
            throw new ProfileApplicationException(ErrorCode.USER_UNAUTHORIZED);
        }

        commentReactionRepository.delete(findCommentReaction);
    }
}
