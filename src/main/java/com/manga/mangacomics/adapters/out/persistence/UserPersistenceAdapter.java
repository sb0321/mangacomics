package com.manga.mangacomics.adapters.out.persistence;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.manga.mangacomics.adapters.out.persistence.entity.UserEntity;
import com.manga.mangacomics.adapters.out.persistence.repository.UserRepository;
import com.manga.mangacomics.application.ports.out.persistence.UserRepositoryPort;
import com.manga.mangacomics.domain.entity.User;
import com.manga.mangacomics.domain.exceptions.UserNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(UserEntity::toDomain)
            .collect(Collectors.toSet());
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("ID:" + id + "에 해당하는 유저가 없습니다."));

        return userEntity.toDomain();
    }

    @Transactional
    @Override
    public User save(User user) {
        UserEntity userEntity = UserEntity.from(user);
        return userRepository.save(userEntity).toDomain();
    }

    @Transactional
    @Override
    public void delete(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.deleteById(user.getId());
            return;
        }

        throw new EntityNotFoundException("ID:" + user.getId() + "에 해당하는 유저가 없습니다.");
    }

}
