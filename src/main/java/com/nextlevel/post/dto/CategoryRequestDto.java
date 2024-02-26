package com.nextlevel.post.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryRequestDto {

    private String name;
    private boolean enabled;
}
