package com.nextlevel.post.mapper;

import com.nextlevel.post.dto.request.CommentRequestDto;
import com.nextlevel.post.dto.response.CommentResponseDto;
import com.nextlevel.post.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentRequestDtoToComment(CommentRequestDto commentRequestDto);

    @Mapping(source = "id", target = "commentId")
    CommentResponseDto commentToCommentResponseDto(Comment comment);

    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comments);
}
