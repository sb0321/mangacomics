package com.manga.mangacomics.domain.entity;

public class User {

    private Long id;
    private String username;
    private String email;

    private Credential password;

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

    public Credential getPassword() {
        return password;
    }

    public void setPassword(Credential password) {
        this.password = password;
    }


}
