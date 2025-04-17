package com.manga.mangacomics.application.ports.in;

import java.util.Set;

import com.manga.mangacomics.adapters.out.persistence.entity.UserEntity;

public interface GetUserUseCase {
    Set<UserEntity> getAllUsers();
}
