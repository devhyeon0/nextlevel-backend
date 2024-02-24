package com.nextlevel.post.mapper;

import com.nextlevel.post.dto.CommentRequestDto;
import com.nextlevel.post.dto.CommentResponseDto;
import com.nextlevel.post.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentRequestDtoToComment(CommentRequestDto commentRequestDto);

    @Mapping(source = "id", target = "commentId")
    CommentResponseDto commentToCommentResponseDto(Comment comment);
}