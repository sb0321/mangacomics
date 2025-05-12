package com.manga.mangacomics.domain.port.out.persistence;

import java.util.Set;

import com.manga.mangacomics.domain.entity.User;

public interface UserRepositoryPort {
    Set<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User save(User user);

    void delete(User user);
}
