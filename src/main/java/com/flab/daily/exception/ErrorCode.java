package com.flab.daily.exception;

import lombok.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATE_EMAIL(903, "중복된 이메일입니다.");

    private final int code;
    private final String message;
}
