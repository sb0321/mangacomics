package com.manga.mangacomics.application.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.manga.mangacomics.application.usecase.SaveUserWithCredentialUseCase;
import com.manga.mangacomics.application.usecase.persistence.user.DeleteUserUseCase;
import com.manga.mangacomics.application.usecase.persistence.user.GetUserUseCase;
import com.manga.mangacomics.application.usecase.persistence.user.SaveUserUseCase;
import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.entity.User;
import com.manga.mangacomics.domain.exceptions.UserRegistrationException;
import com.manga.mangacomics.domain.port.out.persistence.UserRepositoryPort;

import jakarta.transaction.Transactional;

@Service
public class UserService implements GetUserUseCase, SaveUserUseCase, DeleteUserUseCase, SaveUserWithCredentialUseCase {

    private final UserRepositoryPort userJpaPersistenceAdapter;

    private final UserRepositoryPort userMyBatisPersistenceAdapter;

    public UserService(@Qualifier("userJpaPersistenceAdapter") UserRepositoryPort userJpaPersistenceAdapter,
            @Qualifier("userMyBatisPersistenceAdapter") UserRepositoryPort userMyBatisPersistenceAdapter) {
        this.userJpaPersistenceAdapter = userJpaPersistenceAdapter;
        this.userMyBatisPersistenceAdapter = userMyBatisPersistenceAdapter;
    }

    @Override
    public Set<User> getAllUsers() {
        return userMyBatisPersistenceAdapter.getAllUsers();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userJpaPersistenceAdapter.getUserById(id));
    }

    @Transactional
    @Override
    public User save(User user) {
        validateRegistration(user);
        return userJpaPersistenceAdapter.save(user);
    }

    private void validateRegistration(User user) {
        if (isUsernameDuplicated(user.getUsername())) {
            throw new UserRegistrationException("동일한 유저 이름이 이미 있습니다.");
        }

        if (isEmailDuplicated(user.getEmail())) {
            throw new UserRegistrationException("동일한 이메일이 이미 있습니다.");
        }
    }

    private boolean isEmailDuplicated(String email) {
        return userJpaPersistenceAdapter.existsByEmail(email);
    }

    private boolean isUsernameDuplicated(String username) {
        return userJpaPersistenceAdapter.existsByUsername(username);
    }

    @Transactional
    @Override
    public void save(User user, Credential credential) {
        user.setCredential(credential);
        userJpaPersistenceAdapter.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userJpaPersistenceAdapter.delete(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userJpaPersistenceAdapter.getUserByEmail(email));
    }

}
