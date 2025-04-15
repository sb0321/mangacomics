package com.manga.mangacomics.application.ports.in;

import java.util.Set;

import com.manga.mangacomics.domain.entities.User;

public interface GetUserUseCase {
    Set<User> getAllUsers();
}
