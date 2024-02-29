package com.nextlevel.domain.post.service;

import com.nextlevel.domain.post.dto.request.PostReactionRequestDto;
import com.nextlevel.domain.post.mapper.PostReactionMapper;
import com.nextlevel.domain.post.repository.PostReactionRepository;
import com.nextlevel.domain.post.dto.response.PostReactionResponseDto;
import com.nextlevel.domain.post.entity.PostReaction;
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

    public PostReactionResponseDto updateReaction(Long postReactionId, PostReactionRequestDto postReactionRequestDto) {
        PostReaction findPostReaction = postReactionRepository.findById(postReactionId)
                .orElseThrow(() -> new IllegalArgumentException("확인된 반응이 없습니다."));

        findPostReaction.update(postReactionRequestDto);

        return mapper.postReactionToPostReactionResponseDto(findPostReaction);
    }

    @Transactional(readOnly = true)
    public PostReactionResponseDto findPostReaction(Long postReactionId) {
        PostReaction findPostReaction = postReactionRepository.findById(postReactionId)
                .orElseThrow(() -> new IllegalArgumentException("확인된 반응이 없습니다."));

        return mapper.postReactionToPostReactionResponseDto(findPostReaction);
    }

    @Transactional(readOnly = true)
    public List<PostReactionResponseDto> findAllPostReaction() {
        List<PostReaction> allPostReaction = postReactionRepository.findAll();

        return mapper.allPostReactionToPostReactionResponseDtos(allPostReaction);
    }

    public void deleteReaction(Long postReactionId) {
        postReactionRepository.deleteById(postReactionId);
    }
}
