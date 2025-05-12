package com.manga.mangacomics.adapter.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class PasswordEncoderAdapterTest {

    private final PasswordEncoderAdapter passwordEncoderAdapter = new PasswordEncoderAdapter(
            new BCryptPasswordEncoder());

    @Test
    void 비밀번호_인코딩이_잘_되는지_테스트() {
        String rawPassword = "passwordTest";
        String encodedPassword = passwordEncoderAdapter.encode(rawPassword);

        assertTrue(passwordEncoderAdapter.matches(rawPassword, encodedPassword));
    }

    @Test
    void 비밀번호가_다를때_제대로_나오는지_테스트() {
        String rawPassword = "passwordTest";
        String encodedPassword = passwordEncoderAdapter.encode(rawPassword);

        assertFalse(passwordEncoderAdapter.matches("wrongPassword", encodedPassword));
    }
}