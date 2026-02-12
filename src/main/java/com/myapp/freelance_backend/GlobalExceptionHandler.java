package com.myapp.freelance_backend;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationErrors(MethodArgumentNotValidException exception) {
        List errors = new ArrayList();
        for(FieldError ex : exception.getBindingResult().getFieldErrors()){
            errors.add(ex.getDefaultMessage());
        }
        String errorMessage = String.join(",",errors);
    return ResponseEntity.status(400).body(new ApiResponse("error","Invalid data format :" + errorMessage ,null));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleMessageNotReadableErrors(HttpMessageNotReadableException exception) {
        return ResponseEntity.status(400).body(new ApiResponse("error",exception.getMostSpecificCause().getMessage(),null));

    }
}
