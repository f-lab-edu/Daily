package com.flab.daily.exception;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ErrorResponse {
    private int code;
    private String message;
}
