package com.manga.mangacomics.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.manga.mangacomics.application.ports.out.PasswordEncoderPort;
import com.manga.mangacomics.application.ports.out.persistence.CredentialRepositoryPort;
import com.manga.mangacomics.domain.entity.Credential;

class CredentialServiceTest {

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @Mock
    private CredentialRepositoryPort credentialRepositoryPort;

    private CredentialService credentialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        credentialService = new CredentialService(passwordEncoderPort, credentialRepositoryPort);
    }

    @Test
    void Credential_생성이_잘_되는지_테스트() {
        String rawPassword = "password123";
        String hashedPassword = "hashedPassword123";

        when(passwordEncoderPort.encode(rawPassword)).thenReturn(hashedPassword);

        Credential credential = credentialService.createCredential(rawPassword);

        assertEquals(hashedPassword, credential.getHashedPassword());
    }

    @Test
    void Credential_저장이_잘_되는지_테스트() {
        Credential credential = new Credential("hashedPassword123");
        when(credentialRepositoryPort.save(credential)).thenReturn(credential);

        Credential saved = credentialService.save(credential);

        assertEquals(credential, saved);
        verify(credentialRepositoryPort).save(credential);
    }

    @Test
    void verifyPassword_일치하는_경우_true_반환() {
        String rawPassword = "password123";
        String encodedPassword = "hashedPassword123";
    
        when(passwordEncoderPort.matches(rawPassword, encodedPassword)).thenReturn(true);
    
        boolean result = credentialService.verifyPassword(rawPassword, encodedPassword);
    
        assertEquals(true, result);
        verify(passwordEncoderPort).matches(rawPassword, encodedPassword);
    }
    
    @Test
    void verifyPassword_불일치하는_경우_false_반환() {
        String rawPassword = "password123";
        String encodedPassword = "hashedPassword123";
    
        when(passwordEncoderPort.matches(rawPassword, encodedPassword)).thenReturn(false);
    
        boolean result = credentialService.verifyPassword(rawPassword, encodedPassword);
    
        assertEquals(false, result);
        verify(passwordEncoderPort).matches(rawPassword, encodedPassword);
    }    
}
