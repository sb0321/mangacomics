package com.manga.mangacomics.adapter.out.persistence.mybatis.entity;

import org.apache.ibatis.type.Alias;

import com.manga.mangacomics.domain.entity.User;

@Alias("UserMyBatisEntity")
public class UserMyBatisEntity {

    private Long id;

    private String username;

    private String email;

    private CredentialMyBatisEntity credential;

    public static UserMyBatisEntity from(User user) {
        UserMyBatisEntity userMyBatisEntity = new UserMyBatisEntity();

        userMyBatisEntity.setId(user.getId());
        userMyBatisEntity.setEmail(user.getEmail());
        userMyBatisEntity.setUsername(user.getUsername());

        return userMyBatisEntity;
    }

    public User toDomain() {
        User user = new User();

        user.setId(id);
        user.setEmail(email);
        user.setUsername(username);
        user.setCredential(credential.toDomain());

        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CredentialMyBatisEntity getCredential() {
        return credential;
    }

    public void setCredential(CredentialMyBatisEntity credential) {
        this.credential = credential;
    }

}
