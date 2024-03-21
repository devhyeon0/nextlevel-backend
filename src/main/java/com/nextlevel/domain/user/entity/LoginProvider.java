package com.nextlevel.domain.user.entity;

import lombok.Getter;

@Getter
public enum LoginProvider {

    BASIC("내부"),
    NAVER("네이버"),
    KAKAO("카카오"),
    GOOGLE("구글");

    private final String name;

    LoginProvider(String name) {
        this.name = name;
    }
}
