package com.manga.mangacomics.application.ports.in;

import java.util.Set;

import com.manga.mangacomics.domain.entity.User;

public interface GetUserUseCase {
    Set<User> getAllUsers();

    User getUserById(Long id);
}
