package com.nextlevel.global.codes;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 유저 예외 처리
    USER_NOT_FOUND(404, "404", "유저를 찾을 수 없습니다."),
    USER_UNAUTHORIZED(401, "401", "권한이 없습니다."),

    // 게시글 예외 처리
    POST_NOT_FOUND(404, "404", "게시글을 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(404, "404", "카테고리를 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(404, "404", "댓글을 찾을 수 없습니다."),
    REACTION_NOT_FOUND(404, "404", "확인된 반응이 없습니다."),

    // 로그인 예외 처리
    TOKEN_NOT_VALID(404, "404", "유효하지 않은 토큰입니다."),
    TOKEN_NOT_FOUND(404, "404", "존재하지 않는 토큰입니다."),

    BUSINESS_EXCEPTION_ERROR(200, "200", "Business Exception Error"),

    IO_ERROR(400, "400", "I/O 에러"),
    REQUEST_BODY_MISSING_ERROR(400, "400", "Required request body is missing"),
    MISSING_REQUEST_PARAMETER_ERROR(400, "400", "Missing Servlet RequestParameter Exception"),
    JSON_PARSE_ERROR(400, "400", "JsonParse Exception"),
    BAD_REQUEST_ERROR(400, "400", "Bad Request Exception"),

    NOT_VALID_ERROR(404, "404", "Handle Validation Exception"),
    NOT_FOUND_ERROR(404, "404", "Not Found Exception"),
    NULL_POINT_ERROR(404, "404", "Null Point Exception"),

    INTERNAL_SEVER_ERROR(500, "500", "Internal Server Error");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
