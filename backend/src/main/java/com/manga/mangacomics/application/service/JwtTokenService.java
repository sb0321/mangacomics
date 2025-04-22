package com.manga.mangacomics.application.service;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.manga.mangacomics.adapters.in.web.dto.UserLoginRequest;
import com.manga.mangacomics.application.ports.in.CredentialUseCase;
import com.manga.mangacomics.application.ports.in.GetUserUseCase;
import com.manga.mangacomics.application.ports.in.JwtTokenUseCase;
import com.manga.mangacomics.domain.entity.User;
import com.manga.mangacomics.domain.exceptions.InvalidUserPasswordException;
import com.manga.mangacomics.domain.exceptions.UserNotFoundException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenService implements JwtTokenUseCase {

    private static final String SECRET_KEY = "your-very-secret-key-should-be-long-enough-for-hs256";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    private final GetUserUseCase getUserUseCase;
    private final CredentialUseCase credentialUseCase;

    public JwtTokenService(GetUserUseCase getUserUseCase, CredentialUseCase credentialUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.credentialUseCase = credentialUseCase;
    }

    @Override
    public String generateJwtToken(UserLoginRequest request) {
        User user = getUserUseCase.getUserByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        // 비밀번호 검증 로직
        boolean isPasswordValid = credentialUseCase.verifyPassword(
                request.getPassword(),
                user.getCredential().getHashedPassword()
        );

        if (!isPasswordValid) {
            throw new InvalidUserPasswordException("비밀번호가 올바르지 않습니다.");
        }

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        // JWT claims 설정 (이메일을 subject로 사용)
        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }
}