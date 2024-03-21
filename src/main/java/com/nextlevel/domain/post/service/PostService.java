package com.nextlevel.domain.post.service;

import com.nextlevel.domain.post.dto.response.PostResponseDto;
import com.nextlevel.domain.post.entity.Category;
import com.nextlevel.domain.post.entity.Post;
import com.nextlevel.domain.post.mapper.PostMapper;
import com.nextlevel.domain.post.repository.CategoryRepository;
import com.nextlevel.domain.post.repository.PostRepository;
import com.nextlevel.domain.post.dto.request.PostRequestDto;
import com.nextlevel.domain.user.dto.SecurityUserDetailsDto;
import com.nextlevel.domain.user.entity.User;
import com.nextlevel.domain.user.repository.UserRepository;
import com.nextlevel.global.codes.ErrorCode;
import com.nextlevel.global.exception.ProfileApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PostMapper postMapper;

    public void createPost(PostRequestDto postRequestDto, SecurityUserDetailsDto userPrincipal) {
        User user = userRepository.findById(userPrincipal.getUserDto().userId())
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.USER_NOT_FOUND));
        Category category = categoryRepository.findByName(postRequestDto.getCategoryName())
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.CATEGORY_NOT_FOUND));

        Post post = postMapper.postRequestDtoToPost(postRequestDto);
        post.mappingUser(user);
        post.mappingCategory(category);

        postRepository.save(post);
    }

    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto, SecurityUserDetailsDto userPrincipal) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.POST_NOT_FOUND));

        if(!(userPrincipal.getUserDto().userId() == post.getUser().getUserId())) {
            throw new ProfileApplicationException(ErrorCode.USER_UNAUTHORIZED);
        }
        post.update(postRequestDto);

        return postMapper.postToPostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public PostResponseDto findPost(Long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.POST_NOT_FOUND));

        return postMapper.postToPostResponseDto(findPost);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findPosts() {
        List<Post> posts = postRepository.findAll();

        return postMapper.postsToPostResponseDtos(posts);
    }

    public void deletePost(Long postId, SecurityUserDetailsDto userPrincipal) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.POST_NOT_FOUND));

        if(!(userPrincipal.getUserDto().userId() == post.getUser().getUserId())) {
            throw new ProfileApplicationException(ErrorCode.USER_UNAUTHORIZED);
        }

        postRepository.delete(post);
    }

    public void addViewCount(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.POST_NOT_FOUND));
        post.addViewCount();
    }
}
