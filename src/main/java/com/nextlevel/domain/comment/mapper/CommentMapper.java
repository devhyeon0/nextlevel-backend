package com.nextlevel.domain.comment.mapper;

import com.nextlevel.domain.comment.dto.request.CommentRequestDto;
import com.nextlevel.domain.comment.dto.response.CommentResponseDto;
import com.nextlevel.domain.comment.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentRequestDtoToComment(CommentRequestDto commentRequestDto);
    CommentResponseDto commentToCommentResponseDto(Comment comment);
    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comments);
}
