package com.manga.mangacomics.domain.entity;

public class Credential {

    private String hashedPassword;


    public Credential() {
    }

    public Credential(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
