package com.manga.mangacomics.domain.exceptions;

public class InvalidUserPasswordException extends RuntimeException {

    public InvalidUserPasswordException(String message) {
        super(message);
    }
}
