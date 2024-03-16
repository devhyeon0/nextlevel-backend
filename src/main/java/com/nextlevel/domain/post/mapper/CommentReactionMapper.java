package com.nextlevel.domain.post.mapper;

import com.nextlevel.domain.post.dto.request.CommentReactionRequestDto;
import com.nextlevel.domain.post.dto.response.CommentReactionResponseDto;
import com.nextlevel.domain.post.entity.CommentReaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentReactionMapper {

    CommentReaction commentReactionRequestDtoToCommentReaction(CommentReactionRequestDto commentReactionRequestDto);
    CommentReactionResponseDto commentReactionToCommentReactionResponseDto(CommentReaction commentReaction);
    List<CommentReactionResponseDto> allCommentReactionToCommentReactionResponseDtos(List<CommentReaction> allCommentReaction);
}
