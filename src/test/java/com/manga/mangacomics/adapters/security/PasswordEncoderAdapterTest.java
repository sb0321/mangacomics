package com.manga.mangacomics.adapters.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;



class PasswordEncoderAdapterTest {

    private final PasswordEncoderAdapter passwordEncoderAdapter = new PasswordEncoderAdapter();

    @Test
    void encode_ShouldReturnEncodedPassword() {
        String rawPassword = "mySecret123";
        String encodedPassword = passwordEncoderAdapter.encode(rawPassword);

        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encodedPassword.startsWith("$2a$") || encodedPassword.startsWith("$2b$") || encodedPassword.startsWith("$2y$"));
    }

    @Test
    void matches_ShouldReturnTrueForMatchingPasswords() {
        String rawPassword = "passwordTest";
        String encodedPassword = passwordEncoderAdapter.encode(rawPassword);

        assertTrue(passwordEncoderAdapter.matches(rawPassword, encodedPassword));
    }

    @Test
    void matches_ShouldReturnFalseForNonMatchingPasswords() {
        String rawPassword = "passwordTest";
        String encodedPassword = passwordEncoderAdapter.encode(rawPassword);

        assertFalse(passwordEncoderAdapter.matches("wrongPassword", encodedPassword));
    }
}