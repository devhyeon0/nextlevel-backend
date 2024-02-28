package com.nextlevel.post.dto.response;

import com.nextlevel.post.entity.ReactionType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostReactionResponseDto {

    private Long id;
    private ReactionType reactionType;
}