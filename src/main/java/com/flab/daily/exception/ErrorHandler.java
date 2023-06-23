package com.flab.daily.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorHandler {

    //@Valid Exception 확인 메소드
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ErrorResponse(400, e.getFieldError().getDefaultMessage());
    }

    //데이터가 DB에 없을 때 Exception Handler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IsExistCheckException.class)
    public ErrorResponse isExistCheckExceptionHandler(IsExistCheckException e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
        return response;
    }

    //데이터 중복시 발생하는 메소드
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateCheckException.class)
    public ErrorResponse DuplicateCheckException(DuplicateCheckException e) {
        return new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
    }
}

