package com.nextlevel.domain.post.service;

import com.nextlevel.domain.post.dto.request.CommentReactionRequestDto;
import com.nextlevel.domain.post.dto.response.CommentReactionResponseDto;
import com.nextlevel.domain.post.entity.CommentReaction;
import com.nextlevel.domain.post.mapper.CommentReactionMapper;
import com.nextlevel.domain.post.repository.CommentReactionRepository;
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
    private final CommentReactionMapper mapper;

    public void createReaction(CommentReactionRequestDto commentReactionRequestDto) {
        commentReactionRepository.save(mapper.commentReactionRequestDtoToCommentReaction(commentReactionRequestDto));
    }

    public CommentReactionResponseDto updateReaction(Long commentReactionId, CommentReactionRequestDto commentReactionRequestDto) {
        CommentReaction findCommentReaction = commentReactionRepository.findById(commentReactionId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.REACTION_NOT_FOUND));

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
    public List<CommentReactionResponseDto> findAllCommentReaction() {
        List<CommentReaction> allCommentReaction = commentReactionRepository.findAll();

        return mapper.allCommentReactionToCommentReactionResponseDtos(allCommentReaction);
    }

    public void deleteReaction(Long commentReactionId) {
        commentReactionRepository.deleteById(commentReactionId);
    }
}
