package com.nextlevel.post.mapper;

import com.nextlevel.post.dto.request.CommentReactionRequestDto;
import com.nextlevel.post.dto.response.CommentReactionResponseDto;
import com.nextlevel.post.entity.CommentReaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentReactionMapper {

    CommentReaction commentReactionRequestDtoToCommentReaction(CommentReactionRequestDto commentReactionRequestDto);

    CommentReactionResponseDto commentReactionToCommentReactionResponseDto(CommentReaction commentReaction);

    List<CommentReactionResponseDto> allCommentReactionToCommentReactionResponseDtos(List<CommentReaction> allCommentReaction);
}
