package com.manga.mangacomics.adapter.out.persistence.jpa.entity;

import com.manga.mangacomics.domain.entity.Credential;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CREDENTIAL")
public class CredentialEntity {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private UserEntity user;

    @Column(nullable = false)
    private String hashedPassword;

    // 기본 생성자
    public CredentialEntity() {}

    public CredentialEntity(UserEntity user, String hashedPassword) {
        this.user = user;
        this.hashedPassword = hashedPassword;
    }

    public static CredentialEntity from(Credential credential) {
        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setHashedPassword(credential.getHashedPassword());
        return credentialEntity;
    }

    public Credential toDomain() {
        Credential credential = new Credential();
        credential.setHashedPassword(hashedPassword);
        return credential;
    }

    public Long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
