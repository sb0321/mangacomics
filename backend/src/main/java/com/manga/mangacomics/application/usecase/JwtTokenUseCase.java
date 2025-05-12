package com.manga.mangacomics.application.usecase;

import com.manga.mangacomics.adapter.in.web.dto.UserLoginRequest;

public interface JwtTokenUseCase {
    String generateJwtToken(UserLoginRequest request);
}