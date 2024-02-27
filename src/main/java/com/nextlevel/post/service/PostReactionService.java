package com.nextlevel.post.service;

import com.nextlevel.post.dto.PostReactionRequestDto;
import com.nextlevel.post.mapper.PostReactionMapper;
import com.nextlevel.post.repository.PostReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostReactionService {

    private final PostReactionRepository postReactionRepository;
    private final PostReactionMapper mapper;

    public void createReaction(PostReactionRequestDto postReactionRequestDto) {
        postReactionRepository.save(mapper.PostReactionRequestDtoToPostReaction(postReactionRequestDto));
    }
}
