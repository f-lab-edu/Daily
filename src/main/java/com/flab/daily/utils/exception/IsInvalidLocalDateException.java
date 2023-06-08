package com.flab.daily.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IsInvalidLocalDateException extends RuntimeException{
    private final ErrorCode errorCode;
}
