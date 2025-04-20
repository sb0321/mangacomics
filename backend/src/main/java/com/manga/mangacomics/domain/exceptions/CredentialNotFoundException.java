package com.manga.mangacomics.domain.exceptions;

public class CredentialNotFoundException extends RuntimeException {
    
    public CredentialNotFoundException(String message) {
        super(message);
    }
}
