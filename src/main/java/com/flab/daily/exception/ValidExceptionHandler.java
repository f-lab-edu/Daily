package com.flab.daily.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> restApiException(MethodArgumentNotValidException e) {

        String errorMessage = getValidExceptionErrorMessage(e.getBindingResult().getAllErrors());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }


    private String getValidExceptionErrorMessage(List<ObjectError> allErrors) {

        String errorMessage = "";

        for (ObjectError error : allErrors) {
            errorMessage += error.getDefaultMessage() + " | ";
        }

        return errorMessage.substring(0, errorMessage.length() - 3);
    }


}
