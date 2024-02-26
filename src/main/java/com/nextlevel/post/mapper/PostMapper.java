package com.nextlevel.post.mapper;

import com.nextlevel.post.dto.request.PostRequestDto;
import com.nextlevel.post.dto.response.PostResponseDto;
import com.nextlevel.post.entity.Post;
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