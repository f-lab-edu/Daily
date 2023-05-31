package com.flab.daily.utils.exception;

import lombok.Builder;
import lombok.Getter;

// 소모임 등록 API @Valid 전용
@Getter
@Builder
public class ErrorResponse {

    private int code;
    private String result;
    private String message;

}
