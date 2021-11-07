package com.zyke.libraryrest;

import com.zyke.libraryrest.exception.UserNotFoundException;
import com.zyke.libraryrest.exception.UserRegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity userException(Exception exception) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(value = UserRegisterException.class)
    public ResponseEntity userRegisterException(Exception exception) {
        return buildResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY, exception);
    }

    private ResponseEntity buildResponseEntity(HttpStatus status, Exception exception) {
        return ResponseEntity
                .status(status)
                .body(exception.getMessage());
    }

}
