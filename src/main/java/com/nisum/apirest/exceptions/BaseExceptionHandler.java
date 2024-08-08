package com.nisum.apirest.exceptions;

import com.nisum.apirest.constants.AppConstants;
import com.nisum.apirest.model.ErrorMessage;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationExceptions(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ErrorMessage message = new ErrorMessage(AppConstants.INVALID_USER_EMAIL_DUPLICATED_MSG);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(IllegalArgumentException e) {
        ErrorMessage message = new ErrorMessage(e.getMessage());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }
}