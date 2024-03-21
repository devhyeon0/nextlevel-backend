package com.nextlevel.domain.comment.mapper;

import com.nextlevel.domain.comment.dto.request.CommentReactionRequestDto;
import com.nextlevel.domain.comment.dto.response.CommentReactionResponseDto;
import com.nextlevel.domain.comment.entity.CommentReaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentReactionMapper {

    CommentReaction commentReactionRequestDtoToCommentReaction(CommentReactionRequestDto commentReactionRequestDto);
    CommentReactionResponseDto commentReactionToCommentReactionResponseDto(CommentReaction commentReaction);
    List<CommentReactionResponseDto> allCommentReactionToCommentReactionResponseDtos(List<CommentReaction> allCommentReaction);
}
