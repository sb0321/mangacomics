package com.manga.mangacomics.adapter.out.persistence.mybatis;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.manga.mangacomics.adapter.out.persistence.mybatis.entity.UserMyBatisEntity;
import com.manga.mangacomics.adapter.out.persistence.mybatis.exception.InvalidUserIdException;
import com.manga.mangacomics.adapter.out.persistence.mybatis.mapper.UserMapper;
import com.manga.mangacomics.domain.entity.User;
import com.manga.mangacomics.domain.port.out.persistence.UserRepositoryPort;

import io.jsonwebtoken.lang.Collections;

@Component("userMyBatisPersistenceAdapter")
public class UserMybatisPersistenceAdapter implements UserRepositoryPort {

    private final UserMapper userMapper;

    public UserMybatisPersistenceAdapter(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Set<User> getAllUsers() {
        List<UserMyBatisEntity> users = userMapper.findAll();

        if (users.isEmpty()) {
            return Collections.emptySet();
        }

        return users.stream()
                .map(userMyBatisEntity -> userMyBatisEntity.toDomain())
                .collect(Collectors.toSet());
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.findById(id).toDomain();
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.findByEmail(email).toDomain();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userMapper.countByEmail(email) > 0;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userMapper.countByUsername(username) > 0;
    }

    @Override
    public User save(User user) {
        userMapper.insert(UserMyBatisEntity.from(user));
        return user;
    }

    @Override
    public void delete(User user) {
        try {
            userMapper.findById(user.getId());
        } catch (Exception e) {
            throw new InvalidUserIdException(user.getId());
        }
    }
}
