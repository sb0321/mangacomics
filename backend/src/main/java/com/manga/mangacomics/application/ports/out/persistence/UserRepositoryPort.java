package com.manga.mangacomics.application.ports.out.persistence;

import java.util.Set;

import com.manga.mangacomics.domain.entity.User;

public interface UserRepositoryPort {
    Set<User> getAllUsers();

    User getUserById(Long id);

    User save(User user);

    void delete(User user);
}
