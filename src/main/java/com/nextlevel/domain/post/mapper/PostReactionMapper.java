package com.nextlevel.domain.post.mapper;

import com.nextlevel.domain.post.dto.request.PostReactionRequestDto;
import com.nextlevel.domain.post.dto.response.PostReactionResponseDto;
import com.nextlevel.domain.post.entity.PostReaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostReactionMapper {

    PostReaction PostReactionRequestDtoToPostReaction(PostReactionRequestDto postReactionRequestDto);
    PostReactionResponseDto postReactionToPostReactionResponseDto(PostReaction findPostReaction);
    List<PostReactionResponseDto> allPostReactionToPostReactionResponseDtos(List<PostReaction> allPostReaction);
}
