package com.manga.mangacomics.adapter.out.persistence.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.manga.mangacomics.adapter.out.persistence.jpa.entity.CredentialEntity;
import com.manga.mangacomics.adapter.out.persistence.jpa.repository.CredentialRepository;
import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.exceptions.CredentialNotFoundException;

class CredentialJpaPersistenceAdapterTest {

    private CredentialRepository credentialRepository;
    private CredentialJpaPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        credentialRepository = mock(CredentialRepository.class);
        adapter = new CredentialJpaPersistenceAdapter(credentialRepository);
    }

    @Test
    void ID로_패스워드_검색_성공_테스트() {
        Long id = 1L;
        CredentialEntity entity = mock(CredentialEntity.class);
        Credential domainCredential = mock(Credential.class);

        when(credentialRepository.findById(id)).thenReturn(Optional.of(entity));
        when(entity.toDomain()).thenReturn(domainCredential);

        Credential result = adapter.getCredentialById(id);

        assertEquals(domainCredential, result);
        verify(credentialRepository).findById(id);
        verify(entity).toDomain();
    }

    @Test
    void ID_로_패스워드_검색_실패_테스트() {
        Long id = 2L;
        when(credentialRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CredentialNotFoundException.class, () -> adapter.getCredentialById(id));
        verify(credentialRepository).findById(id);
    }

    @Test
    void 패스워드_저장_후_리턴_테스트() {
        Credential domainCredential = mock(Credential.class);
        CredentialEntity entity = mock(CredentialEntity.class);
        CredentialEntity savedEntity = mock(CredentialEntity.class);
        Credential savedDomain = mock(Credential.class);

        mockStatic(CredentialEntity.class);
        when(CredentialEntity.from(domainCredential)).thenReturn(entity);
        when(credentialRepository.save(entity)).thenReturn(savedEntity);
        when(savedEntity.toDomain()).thenReturn(savedDomain);

        Credential result = adapter.save(domainCredential);

        assertEquals(savedDomain, result);
        verify(credentialRepository).save(entity);
        verify(savedEntity).toDomain();
    }
}