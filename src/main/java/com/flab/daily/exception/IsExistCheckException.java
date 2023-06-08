package com.flab.daily.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IsExistCheckException extends RuntimeException  {
    private final ErrorCode errorCode;
}
