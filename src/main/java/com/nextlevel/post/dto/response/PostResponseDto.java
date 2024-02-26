package com.nextlevel.post.dto.response;

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
     private Integer reportCount;
     private Integer createIp;
}
