package com.nextlevel.domain.post.dto.response;

import com.nextlevel.domain.post.entity.ReactionType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostReactionResponseDto {

    private Long postReactionId;
    private ReactionType reactionType;
}
