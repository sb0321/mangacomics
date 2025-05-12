package com.manga.mangacomics.application.usecase.persistence.user;

import com.manga.mangacomics.domain.entity.User;

public interface DeleteUserUseCase {

    void deleteUser(User user);
}
