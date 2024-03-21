package com.nextlevel.global.dto;

import lombok.Getter;

@Getter
public class SingleResponseDto<T> {

    private final T result;

    public SingleResponseDto(T result) {
        this.result = result;
    }
}
