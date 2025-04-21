package com.manga.mangacomics.application.ports.in;

import java.util.Optional;
import java.util.Set;

import com.manga.mangacomics.domain.entity.User;

public interface GetUserUseCase {
    Set<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);
}
