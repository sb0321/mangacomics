package com.manga.mangacomics.application.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.manga.mangacomics.application.ports.in.GetUserUseCase;
import com.manga.mangacomics.application.ports.out.LoadUserPort;
import com.manga.mangacomics.domain.entities.User;

@Service
public class UserService implements GetUserUseCase {

    private final LoadUserPort loadUserPort;

    public UserService(LoadUserPort loadUserPort) {
        this.loadUserPort = loadUserPort;
    }

    @Override
    public Set<User> getAllUsers() {
        return loadUserPort.loadAllUsers();
    }

}
