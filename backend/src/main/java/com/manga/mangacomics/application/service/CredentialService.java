package com.manga.mangacomics.application.service;

import org.springframework.stereotype.Service;

import com.manga.mangacomics.application.usecase.persistence.credential.CredentialUseCase;
import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.port.out.PasswordEncoderPort;
import com.manga.mangacomics.domain.port.out.persistence.CredentialRepositoryPort;

import jakarta.transaction.Transactional;

@Service
public class CredentialService implements CredentialUseCase {

    private final PasswordEncoderPort passwordEncoderPort;
    private final CredentialRepositoryPort credentialRepositoryPort;

    public CredentialService(PasswordEncoderPort passwordEncoderPort, CredentialRepositoryPort credentialRepositoryPort) {
        this.passwordEncoderPort = passwordEncoderPort;
        this.credentialRepositoryPort = credentialRepositoryPort;
    }

    @Override
    public Credential createCredential(String rawPassword) {
        String hashedPassword = passwordEncoderPort.encode(rawPassword);
        return new Credential(hashedPassword);
    }

    @Transactional
    @Override
    public Credential save(Credential credential) {
        return credentialRepositoryPort.save(credential);
    }

    @Override
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoderPort.matches(rawPassword, encodedPassword);
    }
}
