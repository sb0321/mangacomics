package com.manga.mangacomics.application.ports.in;

import com.manga.mangacomics.domain.entity.User;

public interface DeleteUserUseCase {

    void deleteUser(User user);
}
