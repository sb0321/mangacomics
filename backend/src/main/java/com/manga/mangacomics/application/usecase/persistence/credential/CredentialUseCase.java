package com.manga.mangacomics.application.usecase.persistence.credential;

import com.manga.mangacomics.domain.entity.Credential;

public interface CredentialUseCase {

    Credential createCredential(String rawPassword);

    Credential save(Credential credential);

    boolean verifyPassword(String rawPassword, String encodedPassword);
}
