package com.manga.mangacomics.application.ports.out;

import java.util.Set;

import com.manga.mangacomics.adapters.out.persistence.entity.UserEntity;

public interface UserRepositoryPort {
    Set<UserEntity> loadAllUsers();

}
