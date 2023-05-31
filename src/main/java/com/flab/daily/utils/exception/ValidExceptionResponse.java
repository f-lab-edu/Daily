package com.flab.daily.utils.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 소모임 등록 API @Valid 전용
@Getter
@RequiredArgsConstructor
public class ValidExceptionResponse {
    private String code;
    private String result;
    private String message;

    public ValidExceptionResponse(String code, String result, String message) {
        super();
        this.code = code;
        this.result = result;
        this.message = message;
    }

}
