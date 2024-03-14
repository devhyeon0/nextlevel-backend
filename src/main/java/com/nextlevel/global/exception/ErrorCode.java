package com.nextlevel.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum ErrorCode {

    // 유저 예외 처리
    USER_NOT_FOUND(404, "1001", "유저를 찾을 수 없습니다."),

    // 게시글 예외 처리
    POST_NOT_FOUND(404, "2001", "게시글을 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(404, "2011", "카테고리를 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(404, "2021", "댓글을 찾을 수 없습니다."),
    REACTION_NOT_FOUND(404, "2031", "확인된 반응이 없습니다."),

    // 로그인 예외 처리
    TOKEN_NOT_VALID(404, "3001", "유효하지 않은 토큰입니다."),
    TOKEN_NOT_FOUND(404, "3002", "존재하지 않는 토큰입니다."),

    BUSINESS_EXCEPTION_ERROR(200, "B999", "Business Exception Error"),
    IO_ERROR(400, "3001", "I/O 에러");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
