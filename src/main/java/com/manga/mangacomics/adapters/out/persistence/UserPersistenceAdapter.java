package com.manga.mangacomics.adapters.out.persistence;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.manga.mangacomics.adapters.out.persistence.entity.UserEntity;
import com.manga.mangacomics.adapters.out.persistence.repository.UserRepository;
import com.manga.mangacomics.application.ports.out.UserRepositoryPort;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<UserEntity> loadAllUsers() {
        return Set.copyOf(userRepository.findAll());
    }

}
