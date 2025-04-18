package com.manga.mangacomics.application.services;

import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.manga.mangacomics.application.ports.in.DeleteUserUseCase;
import com.manga.mangacomics.application.ports.in.GetUserUseCase;
import com.manga.mangacomics.application.ports.in.SaveUserUseCase;
import com.manga.mangacomics.application.ports.in.SaveUserWithCredentialUseCase;
import com.manga.mangacomics.application.ports.out.persistence.UserRepositoryPort;
import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.entity.User;
import com.manga.mangacomics.domain.exceptions.UserNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class UserService implements GetUserUseCase, SaveUserUseCase, DeleteUserUseCase, SaveUserWithCredentialUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final CredentialService credentialService;


    public UserService(UserRepositoryPort userRepositoryPort, CredentialService credentialService) {
        this.userRepositoryPort = userRepositoryPort;
        this.credentialService = credentialService;
    }

    @Override
    public Set<User> getAllUsers() {
        return userRepositoryPort.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepositoryPort.getUserById(id);

        if (Objects.isNull(user)) {
            throw new UserNotFoundException("ID:" + id + "에 해당하는 유저가 없습니다.");
        }

        return user;
    }

    @Transactional
    @Override
    public User save(User user) {
        return userRepositoryPort.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userRepositoryPort.delete(user);
    }

    @Override
    public void save(User user, Credential credential) {
        User savedUser = userRepositoryPort.save(user);
        
        savedUser.setPassword(credential);
    }

}
