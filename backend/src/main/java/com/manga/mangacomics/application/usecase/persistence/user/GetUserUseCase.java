package com.manga.mangacomics.application.usecase.persistence.user;

import java.util.Optional;
import java.util.Set;

import com.manga.mangacomics.domain.entity.User;

public interface GetUserUseCase {
    Set<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);
}
