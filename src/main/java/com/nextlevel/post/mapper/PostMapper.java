package com.nextlevel.post.mapper;

import com.nextlevel.post.dto.PostRequestDto;
import com.nextlevel.post.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post postRequestDtoToPost(PostRequestDto postRequestDto);
}