package com.flab.daily.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorHandler {

    /*@Valid Exception*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ErrorResponse(400, e.getFieldError().getDefaultMessage());
    }

    /*데이터가 DB에 없을 때*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IsExistCheckException.class)
    public ErrorResponse isExistCheckExceptionHandler(IsExistCheckException e) {
        return new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
    }

    /*데이터 중복*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateCheckException.class)
    public ErrorResponse duplicateCheckException(DuplicateCheckException e) {
        return new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
    }

    /*데이터 초과 시*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IndexOutOfException.class)
    public ErrorResponse indexOutOfException(IndexOutOfException e) {
        return new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
    }

    /*JWT 전용*/
    @ExceptionHandler(JwtCustomException.class)
    public ErrorResponse jwtCustomException(JwtCustomException e) {
        return new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
    }
}

