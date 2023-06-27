package com.flab.daily.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND_CATEGORY(900,"유효하지 않은 카테고리입니다."),
    NOT_FOUND_EMAIL(900,"유효하지 않는 Email입니다."),
    NOT_FOUND_MEETING(900,"유효하지 않는 소모임입니다."),

    VALUE_ALREADY_EXISTS(901, "이미 존재하는 데이터입니다.");

    private final int code;
    private final String message;
}
