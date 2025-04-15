package com.manga.mangacomics.application.services;

import org.springframework.stereotype.Service;

import com.manga.mangacomics.application.ports.out.PasswordEncoderPort;
import com.manga.mangacomics.domain.entities.Credential;

@Service
public class CredentialService {

    private final PasswordEncoderPort passwordEncoderPort;

    public CredentialService(PasswordEncoderPort passwordEncoderPort) {
        this.passwordEncoderPort = passwordEncoderPort;
    }

    public Credential createCredential(String rawPassword) {
        String hashedPassword = passwordEncoderPort.encode(rawPassword);
        return new Credential(hashedPassword);
    }

    public boolean verifyCredential(String rawPassword, Credential credential) {
        return passwordEncoderPort.matches(rawPassword, credential.getHashedPassword());
    }
}
