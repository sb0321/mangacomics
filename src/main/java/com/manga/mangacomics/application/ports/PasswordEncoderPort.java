package com.manga.mangacomics.application.ports;

public interface PasswordEncoderPort {
    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
