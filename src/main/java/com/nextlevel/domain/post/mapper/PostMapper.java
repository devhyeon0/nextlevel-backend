package com.nextlevel.domain.post.mapper;

import com.nextlevel.domain.post.dto.response.PostResponseDto;
import com.nextlevel.domain.post.entity.Post;
import com.nextlevel.domain.post.dto.request.PostRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post postRequestDtoToPost(PostRequestDto postRequestDto);

    @Mapping(source = "id", target = "postId")
    PostResponseDto postToPostResponseDto(Post post);

    @Mapping(source = "postId", target = "id")
    Post postResponseDtoToPost(PostResponseDto postResponseDto);

    List<PostResponseDto> postsToPostResponseDtos(List<Post> posts);
}