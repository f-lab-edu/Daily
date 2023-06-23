package com.flab.daily.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    //@Valid Exception Handler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorResponse response = new ErrorResponse(400, e.getFieldError().getDefaultMessage());
        return response;
    }

    //데이터가 DB에 없을 때 Exception Handler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IsExistCheckException.class)
    public ErrorResponse isExistCheckExceptionHandler(IsExistCheckException e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
        return response;
    }

    //데이터 중복 Exception Handler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateCheckException.class)
    public ErrorResponse duplicateCheckExceptionHandler(DuplicateCheckException e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
        return response;
    }

    //타입 불일치 Exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse notReadableExceptionHandler(HttpMessageNotReadableException e) {
        ErrorResponse response = ErrorResponse.builder().code(ErrorCode.INVALID_DATATYPE.getCode())
                .message(ErrorCode.NOT_FOUND_MEETING.getMessage()).build();
        return response;
    }

    //잘못된 파라미터값 Exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponse notReadableExceptionHandler(MethodArgumentTypeMismatchException e) {
        ErrorResponse response = ErrorResponse.builder().code(ErrorCode.INVALID_INPUT_PARAMETER.getCode())
                .message(ErrorCode.INVALID_INPUT_PARAMETER.getMessage()).build();
        return response;
    }
}
