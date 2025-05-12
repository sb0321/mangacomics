package com.manga.mangacomics.adapter.in.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.manga.mangacomics.domain.exceptions.UserRegistrationException;
import com.manga.mangacomics.domain.exceptions.UserNotFoundException;

@ControllerAdvice
public class GlobalAuthExceptionHandler {

    @ExceptionHandler({UserRegistrationException.class, UserNotFoundException.class})
    public ResponseEntity<Object> handleUserExceptions(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
