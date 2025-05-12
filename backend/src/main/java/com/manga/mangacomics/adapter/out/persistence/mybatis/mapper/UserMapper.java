package com.manga.mangacomics.adapter.out.persistence.mybatis.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.manga.mangacomics.adapter.out.persistence.mybatis.entity.UserMyBatisEntity;

@Mapper
public interface UserMapper {

    Collection<UserMyBatisEntity> findAll();

    UserMyBatisEntity findById(@Param("id") Long id);

    UserMyBatisEntity findByEmail(@Param("email") String email);

    int countByEmail(@Param("email") String email);

    int countByUsername(@Param("username") String username);

    void insert(UserMyBatisEntity userMyBatisEntity);

    void deleteById(@Param("id") Long id);
}
