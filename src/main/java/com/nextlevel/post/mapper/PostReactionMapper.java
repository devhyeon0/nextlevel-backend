package com.nextlevel.post.mapper;

import com.nextlevel.post.dto.PostReactionRequestDto;
import com.nextlevel.post.entity.PostReaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostReactionMapper {

    PostReaction PostReactionRequestDtoToPostReaction(PostReactionRequestDto postReactionRequestDto);
}
