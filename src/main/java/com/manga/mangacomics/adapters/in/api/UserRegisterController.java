package com.manga.mangacomics.adapters.in.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.manga.mangacomics.application.services.CredentialService;
import com.manga.mangacomics.domain.entities.Credential;
import com.manga.mangacomics.dto.UserRegistrationRequest;
import com.manga.mangacomics.dto.UserRegistrationResponse;

@RestController
@RequestMapping("/api/v1/users")
public class UserRegisterController {

    private final CredentialService credentialService;

    public UserRegisterController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRegistrationRequest request) {
        Credential credential = credentialService.createCredential(request.getPassword());

        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setUsername(request.getUsername());
        response.setEmail(request.getEmail());
        response.setPassword(credential.getHashedPassword());

        return ResponseEntity.ok().body(response);
    }

}
