package com.flab.daily.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_CATEGORY(900, "Validation Failed", "카테고리ID가 유효하지 않는 값입니다.");

    private final int code;
    private final String result;
    private final String message;

}
