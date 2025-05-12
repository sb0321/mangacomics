package com.manga.mangacomics.adapter.out.persistence.mybatis.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;

import com.manga.mangacomics.adapter.out.persistence.mybatis.entity.UserMyBatisEntity;

@Mapper
public interface UserMapper {

    Collection<UserMyBatisEntity> findAll();

    UserMyBatisEntity findById(Long id);

    UserMyBatisEntity findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    void save(UserMyBatisEntity userMyBatisEntity);

    void deleteById(Long id);
}
