package com.nextlevel.domain.post.service;

import com.nextlevel.domain.post.dto.response.PostResponseDto;
import com.nextlevel.domain.post.entity.Post;
import com.nextlevel.domain.post.mapper.PostMapper;
import com.nextlevel.domain.post.repository.PostRepository;
import com.nextlevel.domain.post.dto.request.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper mapper;

    public void createPost(PostRequestDto postRequestDto) {
        postRepository.save(mapper.postRequestDtoToPost(postRequestDto));
    }

    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        post.update(postRequestDto);

        return mapper.postToPostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public PostResponseDto findPost(Long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        return mapper.postToPostResponseDto(findPost);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findPosts() {
        List<Post> posts = postRepository.findAll();

        return mapper.postsToPostResponseDtos(posts);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
