package com.manga.mangacomics.adapters.out.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.manga.mangacomics.domain.entity.User;



class UserEntityTest {

    @Test
    void 생성자_게터_테스트트() {
        long id = 1L;
        String username = "testuser";
        String email = "test@example.com";
        UserEntity user = new UserEntity(id, username, email);

        assertEquals(id, user.getUserId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
    }

    @Test
    void 유저네임_설정_테스트() {
        UserEntity user = new UserEntity(2L, "olduser", "user@example.com");
        user.setUsername("newuser");
        assertEquals("newuser", user.getUsername());
    }

    @Test
    void 이메일_설정_테스트() {
        UserEntity user = new UserEntity(3L, "user", "old@example.com");
        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());
    }

    @Test
    void UserEntity변환_ID_가_없을때_가져오지_않는지_테스트() {
        User user = mock(User.class);
        when(user.getId()).thenReturn(null);
        

        UserEntity userEntity = UserEntity.from(user);

        assertEquals(null, userEntity.getUserId());
    }
}