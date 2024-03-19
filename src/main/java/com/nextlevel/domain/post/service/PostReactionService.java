package com.nextlevel.domain.post.service;

import com.nextlevel.domain.post.dto.request.PostReactionRequestDto;
import com.nextlevel.domain.post.entity.Post;
import com.nextlevel.domain.post.mapper.PostReactionMapper;
import com.nextlevel.domain.post.repository.PostReactionRepository;
import com.nextlevel.domain.post.dto.response.PostReactionResponseDto;
import com.nextlevel.domain.post.entity.PostReaction;
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
public class PostReactionService {

    private final PostReactionRepository postReactionRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostReactionMapper mapper;

    public void createReaction(Long postId, PostReactionRequestDto postReactionRequestDto, SecurityUserDetailsDto userPrincipal) {
        User user = userRepository.findById(userPrincipal.userId())
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.USER_NOT_FOUND));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.POST_NOT_FOUND));

        PostReaction postReaction = mapper.PostReactionRequestDtoToPostReaction(postReactionRequestDto);
        postReaction.mappingUser(user);
        postReaction.mappingPost(post);

        postReactionRepository.save(postReaction);
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
    public List<PostReactionResponseDto> findAllPostReaction(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.POST_NOT_FOUND));
        List<PostReaction> allPostReaction = postReactionRepository.findByPost(post);

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
