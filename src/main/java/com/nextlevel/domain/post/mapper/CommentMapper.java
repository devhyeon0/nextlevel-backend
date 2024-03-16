package com.nextlevel.domain.post.mapper;

import com.nextlevel.domain.post.dto.request.CommentRequestDto;
import com.nextlevel.domain.post.dto.response.CommentResponseDto;
import com.nextlevel.domain.post.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentRequestDtoToComment(CommentRequestDto commentRequestDto);
    CommentResponseDto commentToCommentResponseDto(Comment comment);
    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comments);
}
