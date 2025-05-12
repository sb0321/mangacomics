package com.manga.mangacomics.application.usecase;

import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.entity.User;

public interface SaveUserWithCredentialUseCase {
    void save(User user, Credential credential);
}
