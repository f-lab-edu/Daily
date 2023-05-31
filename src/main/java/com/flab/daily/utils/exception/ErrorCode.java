package com.flab.daily.utils.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // @Valid 외 Exception에 따른 양식

    /* 200 OK : 성공 */
    SUCCESS(200, "OK", "요청에 성공하였습니다."),

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_INPUT_VALUE(400, "BAD_REQUEST", "입력값이 올바르지 않습니다."),
    BAD_REQUEST(400, "BAD_REQUEST","잘못된 요청입니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    UNAUTHENTICATED_USERS(401, "UNAUTHORIZED","인증이 필요합니다."),

    /* 403 FORBIDDEN : 접근권한 없음 */
    ACCESS_DENIED(403, "FORBIDDEN","접근이 거부되었습니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(404, "NOT_FOUND","해당 유저 정보를 찾을 수 없습니다."),
    RESOURCE_NOT_FOUND(404, "NOT_FOUND","해당 정보를 찾을 수 없습니다."),

    /* 405 METHOD_NOT_ALLOWED : 지원하지 않는 HTTP Method */
    METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED","허용되지 않은 요청입니다."),

    /* 409 CONFLICT : 데이터 중복 */
    DUPLICATE_RESOURCE(409, "CONFLICT","데이터가 이미 존재합니다"),

    /* 500 INTERNAL_SERVER_ERROR */
    SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "예기치 못한 오류가 발생하였습니다.");

    private final int code;
    private final String result;
    private final String message;

}
