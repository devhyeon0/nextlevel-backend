package com.nextlevel.post.service;

import com.nextlevel.post.dto.PostReactionRequestDto;
import com.nextlevel.post.dto.PostReactionResponseDto;
import com.nextlevel.post.entity.PostReaction;
import com.nextlevel.post.mapper.PostReactionMapper;
import com.nextlevel.post.repository.PostReactionRepository;
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

        return mapper.postReactionToPostReactionResponseDto(findPostReaction);
    }

    @Transactional(readOnly = true)
    public PostReactionResponseDto findPostReaction(Long postReactionId) {
        PostReaction findPostReaction = postReactionRepository.findById(postReactionId)
                .orElseThrow(() -> new IllegalArgumentException("확인된 반응이 없습니다."));

        return mapper.postReactionToPostReactionResponseDto(findPostReaction);
    }

    public List<PostReactionResponseDto> findAllPostReaction() {
        List<PostReaction> allPostReaction = postReactionRepository.findAll();

        return mapper.allPostReactionToPostReactionResponseDtos(allPostReaction);
    }

    public void deleteReaction(Long postReactionId) {
        postReactionRepository.deleteById(postReactionId);
    }
}
