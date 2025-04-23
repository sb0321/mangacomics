package com.manga.mangacomics.adapters.in.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manga.mangacomics.adapters.in.web.dto.UserLoginRequest;
import com.manga.mangacomics.adapters.in.web.dto.UserLoginResponse;
import com.manga.mangacomics.adapters.in.web.dto.UserRegistrationRequest;
import com.manga.mangacomics.adapters.in.web.dto.UserRegistrationResponse;
import com.manga.mangacomics.application.ports.in.CredentialUseCase;
import com.manga.mangacomics.application.ports.in.JwtTokenUseCase;
import com.manga.mangacomics.application.ports.in.SaveUserUseCase;
import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.entity.User;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthenticationController {

    private final SaveUserUseCase saveUserUseCase;
    private final CredentialUseCase credentialUseCase;
    private final JwtTokenUseCase jwtTokenUseCase;

    public UserAuthenticationController(SaveUserUseCase saveUserUseCase,
                                        CredentialUseCase credentialUseCase,
                                        JwtTokenUseCase jwtTokenUseCase) {
        this.saveUserUseCase = saveUserUseCase;
        this.credentialUseCase = credentialUseCase;
        this.jwtTokenUseCase = jwtTokenUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerUser(@RequestBody @Valid UserRegistrationRequest request) {
        User user = request.toUser();
        Credential credential = credentialUseCase.createCredential(request.getPassword());

        user.setCredential(credential);
        User savedUser = saveUserUseCase.save(user);
        return ResponseEntity.ok().body(UserRegistrationResponse.from(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest request) {
        String token = jwtTokenUseCase.generateJwtToken(request);
        return ResponseEntity.ok().body(UserLoginResponse.from(token));
    }
    

}
