package com.nextlevel.post.mapper;

import com.nextlevel.post.dto.CommentRequestDto;
import com.nextlevel.post.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentRequestDtoToComment(CommentRequestDto commentRequestDto);
}
