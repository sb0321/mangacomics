package com.manga.mangacomics.application.ports.out.persistence;

import com.manga.mangacomics.domain.entity.Credential;

public interface CredentialRepositoryPort {
    Credential getCredentialById(Long id);

    Credential save(Credential credential);
}
