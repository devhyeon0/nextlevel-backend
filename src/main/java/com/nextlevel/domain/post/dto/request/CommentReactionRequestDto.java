package com.nextlevel.domain.post.dto.request;

import com.nextlevel.domain.post.entity.ReactionType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentReactionRequestDto {

    private ReactionType reactionType;
}
