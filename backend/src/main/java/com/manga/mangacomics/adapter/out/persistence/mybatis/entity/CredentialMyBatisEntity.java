package com.manga.mangacomics.adapter.out.persistence.mybatis.entity;

import com.manga.mangacomics.domain.entity.Credential;

public class CredentialMyBatisEntity {

    private String hashedPassword;

    public Credential toDomain() {
        Credential credential = new Credential();
        credential.setHashedPassword(hashedPassword);

        return credential;
    }

}
