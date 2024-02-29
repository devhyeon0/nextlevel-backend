package com.nextlevel.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponseDto {

    private Long categoryId;
    private String name;
    private boolean enabled;
}
