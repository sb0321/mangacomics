package com.manga.mangacomics.adapter.in.web.controllers;

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
import com.manga.mangacomics.adapter.in.web.dto.UserLoginRequest;
import com.manga.mangacomics.adapter.in.web.dto.UserLoginResponse;
import com.manga.mangacomics.adapter.in.web.dto.UserRegistrationRequest;
import com.manga.mangacomics.adapter.security.SpringSecurityConfig;
import com.manga.mangacomics.application.usecase.JwtTokenUseCase;
import com.manga.mangacomics.application.usecase.persistence.credential.CredentialUseCase;
import com.manga.mangacomics.application.usecase.persistence.user.SaveUserUseCase;
import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.entity.User;

@WebMvcTest(UserAuthenticationController.class)
@Import(SpringSecurityConfig.class)
class UserRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SaveUserUseCase saveUserUseCase;

    @MockitoBean
    private CredentialUseCase credentialUseCase;

    @MockitoBean
    private JwtTokenUseCase jwtTokenUseCase;

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

        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(credentialUseCase).createCredential("password123");
        verify(saveUserUseCase).save(any(User.class));
    }

    @Test
    void login_Jwt토큰_정상_동작_MockMvc_테스트() throws Exception {
        when(jwtTokenUseCase.generateJwtToken(any(UserLoginRequest.class))).thenReturn("mockedToken");

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password123");

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(UserLoginResponse.from("mockedToken"))));

        verify(jwtTokenUseCase).generateJwtToken(any(UserLoginRequest.class));

    }
}