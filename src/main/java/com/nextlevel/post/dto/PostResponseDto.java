package com.nextlevel.post.dto;

import com.nextlevel.post.entity.PostStatus;
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
     private Integer notify;
     private Integer createIp;
}
