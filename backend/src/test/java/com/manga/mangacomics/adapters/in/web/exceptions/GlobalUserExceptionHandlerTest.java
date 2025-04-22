package com.manga.mangacomics.adapters.in.web.exceptions;

import com.manga.mangacomics.domain.exceptions.UserRegistrationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalUserExceptionHandlerTest {

    @Test
    void handleUserRegistrationException_예외처리_테스트() {
        // given
        GlobalAuthExceptionHandler handler = new GlobalAuthExceptionHandler();
        String errorMessage = "회원가입 중 오류 발생";
        UserRegistrationException exception = new UserRegistrationException(errorMessage);

        // when
        ResponseEntity<Object> response = handler.handleUserExceptions(exception);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }
}