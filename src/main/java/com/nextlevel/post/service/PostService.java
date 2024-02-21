package com.nextlevel.post.service;

import com.nextlevel.post.dto.PostRequestDto;
import com.nextlevel.post.entity.Post;
import com.nextlevel.post.mapper.PostMapper;
import com.nextlevel.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper mapper;

    public void createPost(PostRequestDto postRequestDto) {
        postRepository.save(mapper.postRequestDtoToPost(postRequestDto));
    }
}
