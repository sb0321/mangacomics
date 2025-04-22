package com.manga.mangacomics.application.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.manga.mangacomics.application.ports.in.DeleteUserUseCase;
import com.manga.mangacomics.application.ports.in.GetUserUseCase;
import com.manga.mangacomics.application.ports.in.SaveUserUseCase;
import com.manga.mangacomics.application.ports.in.SaveUserWithCredentialUseCase;
import com.manga.mangacomics.application.ports.out.persistence.UserRepositoryPort;
import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.entity.User;
import com.manga.mangacomics.domain.exceptions.UserRegistrationException;

import jakarta.transaction.Transactional;

@Service
public class UserService implements GetUserUseCase, SaveUserUseCase, DeleteUserUseCase, SaveUserWithCredentialUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Set<User> getAllUsers() {
        return userRepositoryPort.getAllUsers();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepositoryPort.getUserById(id));
    }

    @Transactional
    @Override
    public User save(User user) {
        validateRegistration(user);
        return userRepositoryPort.save(user);
    }

    private void validateRegistration(User user) {
        if (isUsernameDuplicated(user.getUsername())) {
            throw new UserRegistrationException("동일한 유저 이름이 이미 있습니다.");
        }

        if(isEmailDuplicated(user.getEmail())) {
            throw new UserRegistrationException("동일한 이메일이 이미 있습니다.");
        }
    }

    private boolean isEmailDuplicated(String email) {
        return userRepositoryPort.existsByEmail(email);
    }

    private boolean isUsernameDuplicated(String username) {
        return userRepositoryPort.existsByUsername(username);
    }

    @Transactional
    @Override
    public void save(User user, Credential credential) {
        user.setCredential(credential);
        userRepositoryPort.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userRepositoryPort.delete(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepositoryPort.getUserByEmail(email));
    }
}
