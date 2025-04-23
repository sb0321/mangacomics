package com.manga.mangacomics.adapters.out.persistence.entity;

import java.util.List;
import java.util.Objects;

import com.manga.mangacomics.domain.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CredentialEntity credential;

    @OneToMany(mappedBy = "user")
    private List<NovelEntity> novels;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        
        if (Objects.nonNull(user.getId())) {
            userEntity.setUserId(user.getId());
        } 

        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        return userEntity;
    }

    public User toDomain() {
        User user = new User();

        user.setId(userId);
        user.setEmail(email);
        user.setUsername(username);
        user.setCredential(credential.toDomain());
        
        return user;
    }

    public List<NovelEntity> getNovels() {
        return novels;
    }

    public CredentialEntity getCredential() {
        return credential;
    }


    public void setCredential(CredentialEntity credential) {
        this.credential = credential;
    }


    public UserEntity() {
    }


    public UserEntity(long id, String username, String email) {
        this.userId = id;
        this.username = username;
        this.email = email;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    

}
