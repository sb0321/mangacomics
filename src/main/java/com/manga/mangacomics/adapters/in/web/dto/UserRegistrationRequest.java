package com.manga.mangacomics.adapters.in.web.dto;

import com.manga.mangacomics.domain.entity.User;

public class UserRegistrationRequest {

    private String username;
    private String email;
    private String password;

    public User toUser() {
        return new User(this.username, this.email);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
