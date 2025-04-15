package com.manga.mangacomics.application.ports.out;

import java.util.Set;

import com.manga.mangacomics.domain.entities.User;

public interface LoadUserPort {
    Set<User> loadAllUsers();

}
