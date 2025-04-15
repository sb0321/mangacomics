package com.manga.mangacomics.domain.entities;

public class Credential {

    private String hashedPassword;

    public Credential(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

}
