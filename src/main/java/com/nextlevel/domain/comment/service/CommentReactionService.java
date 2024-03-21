package com.nextlevel.domain.comment.service;

import com.nextlevel.domain.comment.dto.request.CommentReactionRequestDto;
import com.nextlevel.domain.comment.dto.response.CommentReactionResponseDto;
import com.nextlevel.domain.comment.entity.Comment;
import com.nextlevel.domain.comment.entity.CommentReaction;
import com.nextlevel.domain.comment.mapper.CommentReactionMapper;
import com.nextlevel.domain.comment.repository.CommentReactionRepository;
import com.nextlevel.domain.comment.repository.CommentRepository;
import com.nextlevel.domain.user.dto.SecurityUserDetailsDto;
import com.nextlevel.domain.user.entity.User;
import com.nextlevel.domain.user.repository.UserRepository;
import com.nextlevel.global.codes.ErrorCode;
import com.nextlevel.global.exception.ProfileApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

        Optional<CommentReaction> reaction = commentReactionRepository.findByUserAndComment(user, comment);
        if (reaction.isPresent()) {
            CommentReaction commentReaction = reaction
                    .orElseThrow(() -> new ProfileApplicationException(ErrorCode.REACTION_NOT_FOUND));
            comment.subtractReactionCount();

            commentReactionRepository.delete(commentReaction);
        } else {
            CommentReaction commentReaction = mapper.commentReactionRequestDtoToCommentReaction(commentReactionRequestDto);
            commentReaction.mappingUser(user);
            commentReaction.mappingComment(comment);
            comment.addReactionCount();

            commentReactionRepository.save(commentReaction);
        }
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
}
