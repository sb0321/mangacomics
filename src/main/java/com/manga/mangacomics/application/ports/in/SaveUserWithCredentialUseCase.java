package com.manga.mangacomics.application.ports.in;

import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.entity.User;

public interface SaveUserWithCredentialUseCase {
    void save(User user, Credential credential);
}
