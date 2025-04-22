package com.manga.mangacomics.application.ports.in;

import com.manga.mangacomics.adapters.in.web.dto.UserLoginRequest;

public interface JwtTokenUseCase {
    String generateJwtToken(UserLoginRequest request);
}