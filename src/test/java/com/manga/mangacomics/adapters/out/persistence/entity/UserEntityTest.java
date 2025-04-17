package com.manga.mangacomics.adapters.out.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



class UserEntityTest {

    @Test
    void testConstructorAndGetters() {
        long id = 1L;
        String username = "testuser";
        String email = "test@example.com";
        UserEntity user = new UserEntity(id, username, email);

        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
    }

    @Test
    void testSetUsername() {
        UserEntity user = new UserEntity(2L, "olduser", "user@example.com");
        user.setUsername("newuser");
        assertEquals("newuser", user.getUsername());
    }

    @Test
    void testSetEmail() {
        UserEntity user = new UserEntity(3L, "user", "old@example.com");
        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());
    }
}