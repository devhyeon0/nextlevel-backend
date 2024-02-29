package com.nextlevel.domain.post.entity;

import lombok.Getter;

@Getter
public enum ReactionType {

    LIKE("좋아요"),
    DISLIKE("싫어요");

    private final String type;

    ReactionType(String type) {
        this.type = type;
    }
}
