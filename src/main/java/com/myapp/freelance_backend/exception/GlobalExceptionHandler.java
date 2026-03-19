package com.myapp.freelance_backend.exception;

import com.myapp.freelance_backend.util.ApiResponse;
import com.myapp.freelance_backend.util.ResponseUtil;
import org.springframework.dao.DataIntegrityViolationException;
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
        List<String> errors = new ArrayList<String>();
        for(FieldError ex : exception.getBindingResult().getFieldErrors()){
            errors.add(ex.getDefaultMessage());
        }
        String errorMessage = String.join(",",errors);
    return ResponseEntity.status(400).body(new ApiResponse("error","Invalid data format :" + errorMessage ,null));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleMessageNotReadableErrors(HttpMessageNotReadableException exception) {
        return ResponseUtil.error(400,exception.getMostSpecificCause().getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArguments(IllegalArgumentException exception) {
        return ResponseUtil.error(400,exception.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleEmailAlreadyExists(ResourceAlreadyExistsException exception){
        return ResponseUtil.error(409, exception.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> roleNotFoundError(ResourceNotFoundException exception){
        return ResponseUtil.error(404, exception.getMessage());
    }
// Thrown when duplicate data violates a DB unique constraint, typically during concurrent (race condition) requests.
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrity(
            DataIntegrityViolationException ex) {
        return ResponseUtil.error(409, "Duplicate entry — resource already exists");
    }
}
