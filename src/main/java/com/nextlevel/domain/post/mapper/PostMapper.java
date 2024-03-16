package com.nextlevel.domain.post.mapper;

import com.nextlevel.domain.post.dto.response.PostResponseDto;
import com.nextlevel.domain.post.entity.Post;
import com.nextlevel.domain.post.dto.request.PostRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "categoryName", target = "category", ignore = true)
    Post postRequestDtoToPost(PostRequestDto postRequestDto);
    PostResponseDto postToPostResponseDto(Post post);
    Post postResponseDtoToPost(PostResponseDto postResponseDto);
    List<PostResponseDto> postsToPostResponseDtos(List<Post> posts);
}