package com.nextlevel.domain.post.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryRequestDto {

    private String name;
    private boolean enabled;
}
