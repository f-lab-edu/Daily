package com.flab.daily.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DuplicateCheckException extends RuntimeException {
    private final ErrorCode errorCode;
}
