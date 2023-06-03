package com.flab.daily.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidCheckException extends RuntimeException  {
    private final ErrorCode errorCode;
}
