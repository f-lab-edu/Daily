package com.flab.daily.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.sql.SQLNonTransientConnectionException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ErrorResponse(400, e.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(DuplicateCheckException.class)
    public ErrorResponse DuplicateCheckException(DuplicateCheckException e) {
        return new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
    }

    @ExceptionHandler({SQLNonTransientConnectionException.class, ConnectException.class})
    public ErrorResponse sqlNonTransientConnectionException(SQLNonTransientConnectionException e) {
        return new ErrorResponse(500, e.getMessage());
    }
}
