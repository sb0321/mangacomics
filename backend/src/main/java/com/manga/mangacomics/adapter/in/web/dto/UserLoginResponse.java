package com.manga.mangacomics.adapter.in.web.dto;

public class UserLoginResponse {

    private String jwtToken;

    public static UserLoginResponse from(String jwtToken) {
        UserLoginResponse response = new UserLoginResponse();
        response.setJwtToken(jwtToken);
        return response;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
