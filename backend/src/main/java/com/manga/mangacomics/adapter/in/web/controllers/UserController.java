package com.manga.mangacomics.adapter.in.web.controllers;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manga.mangacomics.adapter.in.web.dto.UserDTO;
import com.manga.mangacomics.application.usecase.persistence.user.GetUserUseCase;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final GetUserUseCase getUserUseCase;

    public UserController(GetUserUseCase getUserUseCase) {
        this.getUserUseCase = getUserUseCase;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<UserDTO>> getAllUsers() {
        Set<UserDTO> users = getUserUseCase.getAllUsers()
                .stream()
                .map(UserDTO::from)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(users);
    }

}
