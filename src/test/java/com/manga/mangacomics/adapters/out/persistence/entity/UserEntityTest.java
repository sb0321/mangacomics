package com.manga.mangacomics.adapters.out.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



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
}