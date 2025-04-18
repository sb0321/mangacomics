package com.manga.mangacomics.adapters.in.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manga.mangacomics.adapters.in.web.dto.UserRegistrationRequest;
import com.manga.mangacomics.application.ports.in.CredentialUseCase;
import com.manga.mangacomics.application.ports.in.SaveUserUseCase;
import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.entity.User;

@RestController
@RequestMapping("/api/v1/users")
public class UserRegisterController {

    private final SaveUserUseCase saveUserUseCase;
    private final CredentialUseCase credentialUseCase;

    public UserRegisterController(SaveUserUseCase saveUserUseCase, CredentialUseCase credentialUseCase) {
        this.saveUserUseCase = saveUserUseCase;
        this.credentialUseCase = credentialUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest request) {
        User user = request.toUser();

        Credential credential = credentialUseCase.createCredential(request.getPassword());
        credentialUseCase.save(credential);
        saveUserUseCase.save(user);
        return ResponseEntity.ok().body(user);
    }

}
