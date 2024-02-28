package com.nextlevel.post.dto.request;

import com.nextlevel.post.entity.ReactionType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentReactionRequestDto {

    private ReactionType reactionType;
}
