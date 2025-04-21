package com.manga.mangacomics.adapters.in.web.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manga.mangacomics.adapters.in.web.dto.UserLoginRequest;
import com.manga.mangacomics.adapters.in.web.dto.UserRegistrationRequest;
import com.manga.mangacomics.application.ports.in.CredentialUseCase;
import com.manga.mangacomics.application.ports.in.GetUserUseCase;
import com.manga.mangacomics.application.ports.in.JwtTokenUseCase;
import com.manga.mangacomics.application.ports.in.SaveUserUseCase;
import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.entity.User;
import com.manga.mangacomics.domain.exceptions.UserNotFoundException;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthenticationController {

    private final GetUserUseCase getUserUseCase;
    private final SaveUserUseCase saveUserUseCase;
    private final CredentialUseCase credentialUseCase;
    private final JwtTokenUseCase jwtTokenUseCase;

    public UserAuthenticationController(SaveUserUseCase saveUserUseCase,
                                        CredentialUseCase credentialUseCase,
                                        JwtTokenUseCase jwtTokenUseCase,
                                        GetUserUseCase getUserUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.saveUserUseCase = saveUserUseCase;
        this.credentialUseCase = credentialUseCase;
        this.jwtTokenUseCase = jwtTokenUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserRegistrationRequest request) {
        User user = request.toUser();
        Credential credential = credentialUseCase.createCredential(request.getPassword());

        user.setCredential(credential);
        User savedUser = saveUserUseCase.save(user);
        return ResponseEntity.ok().body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> getMethodName(@RequestBody @Valid UserLoginRequest request) {
        Optional<User> user = getUserUseCase.getUserByEmail(request.getEmail());

        if (user.isEmpty()) {
            throw new UserNotFoundException("해당 이메일을 가진 유저가 없습니다.");
        }

        String token = jwtTokenUseCase.generateJwtToken(request);
        return ResponseEntity.ok().body(token);
    }
    

}
