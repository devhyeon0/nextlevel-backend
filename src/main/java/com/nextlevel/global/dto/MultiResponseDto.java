package com.nextlevel.global.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MultiResponseDto<T> {

    private final List<T> result;

    public MultiResponseDto(List<T> result) {
        this.result = result;
    }
}
