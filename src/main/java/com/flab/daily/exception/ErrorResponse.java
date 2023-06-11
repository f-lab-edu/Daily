package com.flab.daily.exception;

import lombok.*;


@Getter
@Builder
public class ErrorResponse {
    private int code;
    private String message;
}
