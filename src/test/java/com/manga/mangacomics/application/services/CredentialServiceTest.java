package com.manga.mangacomics.application.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.manga.mangacomics.application.ports.out.PasswordEncoderPort;
import com.manga.mangacomics.domain.entities.Credential;

class CredentialServiceTest {

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    private CredentialService credentialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        credentialService = new CredentialService(passwordEncoderPort);
    }

    @Test
    void testCreateCredential() {
        String rawPassword = "password123";
        String hashedPassword = "hashedPassword123";

        when(passwordEncoderPort.encode(rawPassword)).thenReturn(hashedPassword);

        Credential credential = credentialService.createCredential(rawPassword);

        assertTrue(credential.getHashedPassword().equals(hashedPassword));
    }

    @Test
    void testVerifyCredential_Success() {
        String rawPassword = "password123";
        String hashedPassword = "hashedPassword123";
        Credential credential = new Credential(hashedPassword);

        when(passwordEncoderPort.matches(rawPassword, hashedPassword)).thenReturn(true);

        boolean result = credentialService.verifyCredential(rawPassword, credential);

        assertTrue(result);
    }

    @Test
    void testVerifyCredential_Failure() {
        String rawPassword = "password123";
        String hashedPassword = "hashedPassword123";
        Credential credential = new Credential(hashedPassword);

        when(passwordEncoderPort.matches(rawPassword, hashedPassword)).thenReturn(false);

        boolean result = credentialService.verifyCredential(rawPassword, credential);

        assertFalse(result);
    }
}