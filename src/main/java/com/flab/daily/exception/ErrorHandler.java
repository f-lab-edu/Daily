package com.flab.daily.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    //@Valid Exception 확인 메소드
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorResponse response = new ErrorResponse(400, e.getFieldError().getDefaultMessage());
        return response;
    }

    //데이터가 DB에 없을 때 발생하는 메소드
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IsExistCheckException.class)
    public ErrorResponse isExistCheckExceptionHandler(IsExistCheckException e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
        return response;
    }

    //데이터가 DB에 없을 때 발생하는 메소드
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateCheckException.class)
    public ErrorResponse duplicateCheckExceptionHandler(DuplicateCheckException e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
        return response;
    }

    //타입 일치 Exception 메소드
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse notReadableExceptionHandler(HttpMessageNotReadableException e) {
        ErrorResponse response = new ErrorResponse(400,  "데이터 타입이 맞지 않습니다.");
        return response;
    }
}
