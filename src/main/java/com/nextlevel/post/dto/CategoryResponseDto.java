package com.nextlevel.post.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponseDto {

    private Long categoryId;
    private String name;
    private boolean enabled;
}
