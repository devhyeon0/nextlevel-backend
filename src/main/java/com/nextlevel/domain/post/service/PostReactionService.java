package com.nextlevel.domain.post.service;

import com.nextlevel.domain.post.dto.request.PostReactionRequestDto;
import com.nextlevel.domain.post.mapper.PostReactionMapper;
import com.nextlevel.domain.post.repository.PostReactionRepository;
import com.nextlevel.domain.post.dto.response.PostReactionResponseDto;
import com.nextlevel.domain.post.entity.PostReaction;
import com.nextlevel.domain.user.dto.SecurityUserDetailsDto;
import com.nextlevel.global.codes.ErrorCode;
import com.nextlevel.global.exception.ProfileApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostReactionService {

    private final PostReactionRepository postReactionRepository;
    private final PostReactionMapper mapper;

    public void createReaction(PostReactionRequestDto postReactionRequestDto) {
        postReactionRepository.save(mapper.PostReactionRequestDtoToPostReaction(postReactionRequestDto));
    }

    public PostReactionResponseDto updateReaction(Long postReactionId, PostReactionRequestDto postReactionRequestDto, SecurityUserDetailsDto userPrincipal) {
        PostReaction findPostReaction = postReactionRepository.findById(postReactionId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.REACTION_NOT_FOUND));

        if(!(userPrincipal.getUserDto().userId() == findPostReaction.getUser().getUserId())) {
            throw new ProfileApplicationException(ErrorCode.USER_UNAUTHORIZED);
        }
        findPostReaction.update(postReactionRequestDto);

        return mapper.postReactionToPostReactionResponseDto(findPostReaction);
    }

    @Transactional(readOnly = true)
    public PostReactionResponseDto findPostReaction(Long postReactionId) {
        PostReaction findPostReaction = postReactionRepository.findById(postReactionId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.REACTION_NOT_FOUND));

        return mapper.postReactionToPostReactionResponseDto(findPostReaction);
    }

    @Transactional(readOnly = true)
    public List<PostReactionResponseDto> findAllPostReaction() {
        List<PostReaction> allPostReaction = postReactionRepository.findAll();

        return mapper.allPostReactionToPostReactionResponseDtos(allPostReaction);
    }

    public void deleteReaction(Long postReactionId, SecurityUserDetailsDto userPrincipal) {
        PostReaction findPostReaction = postReactionRepository.findById(postReactionId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.REACTION_NOT_FOUND));

        if(!(userPrincipal.getUserDto().userId() == findPostReaction.getUser().getUserId())) {
            throw new ProfileApplicationException(ErrorCode.USER_UNAUTHORIZED);
        }

        postReactionRepository.delete(findPostReaction);
    }
}
