package com.manga.mangacomics.domain.entity;

public class Credential {

    private String hashedPassword;

    public Credential(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

}
