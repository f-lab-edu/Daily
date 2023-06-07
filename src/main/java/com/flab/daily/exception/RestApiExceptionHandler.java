package com.flab.daily.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class RestApiExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        this.getValidExceptionErrorMessage(errorResponse, e.getBindingResult().getAllErrors());
        return errorResponse;
    }

    @ExceptionHandler(DuplicateCheckException.class)
    public ErrorResponse DuplicateCheckException(DuplicateCheckException e) {
        return new ErrorResponse(Arrays.asList(e.getErrorCode()));
    }


    private void getValidExceptionErrorMessage(ErrorResponse errorResponse, List<ObjectError> allErrors) {
        List<ErrorCode> list = new ArrayList<>();
        ErrorCode errorcode = new ErrorCode();

        for (ObjectError error : allErrors) {
            errorcode.setStatus(HttpStatus.BAD_REQUEST);
            errorcode.setMessage(error.getDefaultMessage());
            list.add(errorcode);
        }
        errorResponse.setError(list);
    }
}
