package com.manga.mangacomics.adapter.out.persistence.jpa;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.manga.mangacomics.adapter.out.persistence.jpa.entity.CredentialEntity;
import com.manga.mangacomics.adapter.out.persistence.jpa.entity.UserEntity;
import com.manga.mangacomics.adapter.out.persistence.jpa.repository.UserRepository;
import com.manga.mangacomics.domain.entity.User;
import com.manga.mangacomics.domain.exceptions.UserNotFoundException;
import com.manga.mangacomics.domain.port.out.persistence.UserRepositoryPort;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Component("userJpaPersistenceAdapter")
public class UserJpaPersistenceAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    public UserJpaPersistenceAdapter(UserRepository userRepository) {
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
        CredentialEntity credentialEntity = CredentialEntity.from(user.getCredential());

        userEntity.setCredential(credentialEntity);
        credentialEntity.setUser(userEntity);
        
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

    @Override
    public User getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("이메일: " + email + "에 해당하는 유저가 없습니다."));
        return userEntity.toDomain();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
