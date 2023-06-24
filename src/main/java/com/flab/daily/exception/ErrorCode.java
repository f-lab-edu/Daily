package com.flab.daily.exception;

import lombok.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    IS_EXIST_USER_BY_EMAIL(901, "중복된 이메일입니다.");

    private final int code;
    private final String message;
}
