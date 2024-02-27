package com.nextlevel.post.mapper;

import com.nextlevel.post.dto.PostReactionRequestDto;
import com.nextlevel.post.dto.PostReactionResponseDto;
import com.nextlevel.post.entity.PostReaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostReactionMapper {

    PostReaction PostReactionRequestDtoToPostReaction(PostReactionRequestDto postReactionRequestDto);

    @Mapping(source = "id", target = "post_reaction_id")
    PostReactionResponseDto postReactionToPostReactionResponseDto(PostReaction findPostReaction);

    List<PostReactionResponseDto> allPostReactionToPostReactionResponseDtos(List<PostReaction> allPostReaction);
}
