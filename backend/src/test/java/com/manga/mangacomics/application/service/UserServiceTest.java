package com.manga.mangacomics.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.entity.User;
import com.manga.mangacomics.domain.exceptions.UserRegistrationException;
import com.manga.mangacomics.domain.port.out.persistence.UserRepositoryPort;

class UserServiceTest {

    private UserRepositoryPort userJpaPersistenceAdapter;
    private UserRepositoryPort userMyBatisPersistenceAdapter;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userJpaPersistenceAdapter = mock(UserRepositoryPort.class);
        userMyBatisPersistenceAdapter = mock(UserRepositoryPort.class);
        userService = new UserService(userJpaPersistenceAdapter, userMyBatisPersistenceAdapter);
    }

    @Test
    void 모든_유저_불러오기_테스트() {
        Set<User> users = new HashSet<>();
        users.add(new User());
        when(userMyBatisPersistenceAdapter.getAllUsers()).thenReturn(users);

        Set<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        verify(userMyBatisPersistenceAdapter).getAllUsers();
    }

    @Test
    void 유저를_불러오기_테스트() {
        User user = new User();
        when(userJpaPersistenceAdapter.getUserById(1L)).thenReturn(user);

        Optional<User> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userJpaPersistenceAdapter).getUserById(1L);
    }

    @Test
    void 유저ID_가_없을때_테스트() {
        when(userJpaPersistenceAdapter.getUserById(2L)).thenReturn(null);

        Optional<User> result = userService.getUserById(2L);

        assertFalse(result.isPresent());
        verify(userJpaPersistenceAdapter).getUserById(2L);
    }

    @Test
    void 유저_저장_중복_없을때_테스트() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        when(userJpaPersistenceAdapter.existsByUsername("testuser")).thenReturn(false);
        when(userJpaPersistenceAdapter.existsByEmail("test@example.com")).thenReturn(false);
        when(userJpaPersistenceAdapter.save(user)).thenReturn(user);

        User saved = userService.save(user);

        assertEquals(user, saved);
        verify(userJpaPersistenceAdapter).save(user);
    }

    @Test
    void 유저_이름이_중복일때_등록_안되는지_테스트() {
        User user = new User();
        user.setUsername("dupuser");
        user.setEmail("test@example.com");

        when(userJpaPersistenceAdapter.existsByUsername("dupuser")).thenReturn(true);

        UserRegistrationException ex = assertThrows(UserRegistrationException.class, () -> userService.save(user));
        assertEquals("동일한 유저 이름이 이미 있습니다.", ex.getMessage());
        verify(userJpaPersistenceAdapter, never()).save(any());
    }

    @Test
    void 유저_이메일이_중복일때_등록_안되는지_테스트() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("dup@example.com");

        when(userJpaPersistenceAdapter.existsByUsername("testuser")).thenReturn(false);
        when(userJpaPersistenceAdapter.existsByEmail("dup@example.com")).thenReturn(true);

        UserRegistrationException ex = assertThrows(UserRegistrationException.class, () -> userService.save(user));
        assertEquals("동일한 이메일이 이미 있습니다.", ex.getMessage());
        verify(userJpaPersistenceAdapter, never()).save(any());
    }

    @Test
    void 패스워드와_함께_유저_등록_테스트() {
        User user = new User();
        Credential credential = new Credential();

        userService.save(user, credential);

        assertEquals(credential, user.getCredential());
        verify(userJpaPersistenceAdapter).save(user);
    }

    @Test
    void 유저_삭제_테스트() {
        User user = new User();

        userService.deleteUser(user);

        verify(userJpaPersistenceAdapter).delete(user);
    }

    @Test
    void 이메일로_유저_가져오기_테스트() {
        User user = new User();
        when(userJpaPersistenceAdapter.getUserByEmail("test@example.com")).thenReturn(user);

        Optional<User> result = userService.getUserByEmail("test@example.com");

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userJpaPersistenceAdapter).getUserByEmail("test@example.com");
    }

    @Test
    void 없는_유저_이메일로_유저_가져오기_테스트() {
        when(userJpaPersistenceAdapter.getUserByEmail("notfound@example.com")).thenReturn(null);

        Optional<User> result = userService.getUserByEmail("notfound@example.com");

        assertFalse(result.isPresent());
        verify(userJpaPersistenceAdapter).getUserByEmail("notfound@example.com");
    }
}
