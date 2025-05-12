package com.manga.mangacomics.adapter.out.persistence.mybatis;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.manga.mangacomics.adapter.out.persistence.mybatis.entity.UserMyBatisEntity;
import com.manga.mangacomics.adapter.out.persistence.mybatis.exception.InvalidUserIdException;
import com.manga.mangacomics.adapter.out.persistence.mybatis.mapper.UserMapper;
import com.manga.mangacomics.domain.entity.User;
import com.manga.mangacomics.domain.port.out.persistence.UserRepositoryPort;

@Component("userMyBatisPersistenceAdapter")
public class UserMybatisPersistenceAdapter implements UserRepositoryPort {

    private final UserMapper userMapper;

    public UserMybatisPersistenceAdapter(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Set<User> getAllUsers() {
        return userMapper.findAll().stream()
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
        return userMapper.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userMapper.existsByUsername(username);
    }

    @Override
    public User save(User user) {
        userMapper.save(UserMyBatisEntity.from(user));
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
