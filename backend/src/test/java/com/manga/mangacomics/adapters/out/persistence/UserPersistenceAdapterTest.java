package com.manga.mangacomics.adapters.out.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.entity.User;

class UserPersistenceAdapterTest {

    private UserRepository userRepository;
    private UserPersistenceAdapter userPersistenceAdapter;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userPersistenceAdapter = new UserPersistenceAdapter(userRepository);
    }

    @Test
    void 모든_유저를_검색할때_잘_리턴하는지_확인() {
        UserEntity userEntity1 = mock(UserEntity.class);
        UserEntity userEntity2 = mock(UserEntity.class);
        User user1 = mock(User.class);
        User user2 = mock(User.class);

        when(userEntity1.toDomain()).thenReturn(user1);
        when(userEntity2.toDomain()).thenReturn(user2);
        when(userRepository.findAll()).thenReturn(List.of(userEntity1, userEntity2));

        Set<User> result = userPersistenceAdapter.getAllUsers();

        assertEquals(2, result.size());
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void 모든유저를_찾을때_아무도_없을_경우() {
        when(userRepository.findAll()).thenReturn(List.of());

        Set<User> result = userPersistenceAdapter.getAllUsers();

        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void User_단일_저장_테스트() {
        User user = mock(User.class);
        Credential credential = mock(Credential.class);

        when(user.getId()).thenReturn(1L);
        when(user.getPassword()).thenReturn(credential);
        
        when(credential.getHashedPassword()).thenReturn("hashedPassword123");
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User savedUser = userPersistenceAdapter.save(user);

        assertEquals(1L, savedUser.getId());
        assertEquals("hashedPassword123", savedUser.getPassword().getHashedPassword());
        
    }
}