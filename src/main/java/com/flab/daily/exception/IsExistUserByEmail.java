package com.flab.daily.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IsExistUserByEmail extends RuntimeException {
    private final ErrorCode errorCode;
}
