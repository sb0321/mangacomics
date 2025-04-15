package com.manga.mangacomics.domain.entities;

public class User {

    private Long id;
    private String username;
    private String email;

    private Credential password;

    public User(long id, String username, String email) {
        this.id = id;
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

    public Credential getPassword() {
        return password;
    }

    public void setPassword(Credential password) {
        this.password = password;
    }

}
