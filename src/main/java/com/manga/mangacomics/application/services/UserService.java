package com.manga.mangacomics.application.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.manga.mangacomics.adapters.out.persistence.entity.UserEntity;
import com.manga.mangacomics.application.ports.in.GetUserUseCase;
import com.manga.mangacomics.application.ports.out.UserRepositoryPort;

@Service
public class UserService implements GetUserUseCase {

    private final UserRepositoryPort loadUserPort;

    public UserService(UserRepositoryPort loadUserPort) {
        this.loadUserPort = loadUserPort;
    }

    @Override
    public Set<UserEntity> getAllUsers() {
        return loadUserPort.loadAllUsers();
    }

}
