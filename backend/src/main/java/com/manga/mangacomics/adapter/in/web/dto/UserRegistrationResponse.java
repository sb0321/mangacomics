package com.manga.mangacomics.adapter.in.web.dto;

import com.manga.mangacomics.domain.entity.User;

public class UserRegistrationResponse {

    private String username;
    private String email;

    public static UserRegistrationResponse from(User user) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        return response;
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
}
