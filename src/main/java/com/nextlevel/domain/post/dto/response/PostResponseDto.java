package com.nextlevel.domain.post.dto.response;

import com.nextlevel.domain.post.entity.PostStatus;
import com.nextlevel.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponseDto {

     private Long postId;
     private String title;
     private String content;
     private Long views;
     private PostStatus status;
     private Integer reportCount;
     private UserResponseDto user;
     private CategoryResponseDto category;
}
