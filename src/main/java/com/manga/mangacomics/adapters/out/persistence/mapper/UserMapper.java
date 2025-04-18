package com.manga.mangacomics.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.manga.mangacomics.adapters.out.persistence.entity.UserEntity;
import com.manga.mangacomics.domain.entity.User;

@Component
public class UserMapper {


    public User mapToDomain(UserEntity userJpaEntity) {
        return new User(userJpaEntity.getId(), userJpaEntity.getUsername(), userJpaEntity.getEmail());
    }

    public UserEntity mapToJpaEntity(User user) {
        return new UserEntity(user.getId(), user.getUsername(), user.getEmail());
    }
}
