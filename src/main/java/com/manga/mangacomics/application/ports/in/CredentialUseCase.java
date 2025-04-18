package com.manga.mangacomics.application.ports.in;

import com.manga.mangacomics.domain.entity.Credential;

public interface CredentialUseCase {

    Credential createCredential(String rawPassword);

    Credential save(Credential credential);
}
