package com.flab.daily.utils.exception;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ErrorResponse {

    private int code;
    private String result;
    private String message;

}
