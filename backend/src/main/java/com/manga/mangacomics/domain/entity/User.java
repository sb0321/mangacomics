package com.manga.mangacomics.domain.entity;

public class User {

    private Long id;
    private String username;
    private String email;

    private Credential credential;

    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential password) {
        this.credential = password;
    }


}
