package com.manga.mangacomics.adapters.out.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.manga.mangacomics.adapters.out.persistence.entity.UserEntity;
import com.manga.mangacomics.adapters.out.persistence.repository.UserRepository;



class UserPersistenceAdapterTest {

    private UserRepository userRepository;
    private UserPersistenceAdapter userPersistenceAdapter;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userPersistenceAdapter = new UserPersistenceAdapter(userRepository);
    }

    @Test
    void loadAllUsers_returnsSetOfUsers() {
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        Set<UserEntity> result = userPersistenceAdapter.getAllUsers();

        assertEquals(2, result.size());
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void loadAllUsers_returnsEmptySetWhenNoUsers() {
        when(userRepository.findAll()).thenReturn(List.of());

        Set<UserEntity> result = userPersistenceAdapter.getAllUsers();

        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).findAll();
    }
}