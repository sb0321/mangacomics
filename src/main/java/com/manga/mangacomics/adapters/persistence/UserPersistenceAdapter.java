package com.manga.mangacomics.adapters.persistence;

import java.util.Set;

import com.manga.mangacomics.adapters.out.web.persistence.UserRepository;
import com.manga.mangacomics.application.ports.out.LoadUserPort;
import com.manga.mangacomics.domain.entities.User;

public class UserPersistenceAdapter implements LoadUserPort {

    private final UserRepository userRepository;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> loadAllUsers() {
        return Set.copyOf(userRepository.findAll());
    }

}
