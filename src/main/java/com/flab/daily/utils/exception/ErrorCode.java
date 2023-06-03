package com.flab.daily.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_CATEGORY(900, "Validation Failed", "유효하지 않은 카테고리입니다."),
    NOT_FOUND_EMAIL(901, "Validation Failed", "유효하지 않는 Email입니다.");

    private final int code;
    private final String result;
    private final String message;

}
