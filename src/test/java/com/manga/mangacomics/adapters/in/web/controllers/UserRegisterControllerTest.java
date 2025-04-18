package com.manga.mangacomics.adapters.in.web.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manga.mangacomics.adapters.in.web.dto.UserRegistrationRequest;
import com.manga.mangacomics.adapters.security.SpringSecurityConfig;
import com.manga.mangacomics.application.ports.in.CredentialUseCase;
import com.manga.mangacomics.application.ports.in.SaveUserUseCase;
import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.entity.User;

@WebMvcTest(UserRegisterController.class)
@Import(SpringSecurityConfig.class)
class UserRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SaveUserUseCase saveUserUseCase;

    @MockitoBean
    private CredentialUseCase credentialUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    private UserRegistrationRequest request;
    private Credential credential;
    private User savedUser;

    @BeforeEach
    void setUp() {
        request = new UserRegistrationRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");

        credential = new Credential("hashedPassword");
        savedUser = new User();
    }

    @Test
    void registerUser_정상_동작_MockMvc_테스트() throws Exception {
        when(credentialUseCase.createCredential("password123")).thenReturn(credential);
        when(saveUserUseCase.save(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(credentialUseCase).createCredential("password123");
        verify(saveUserUseCase).save(any(User.class));
    }
}