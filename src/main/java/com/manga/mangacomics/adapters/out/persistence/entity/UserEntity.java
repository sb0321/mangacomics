package com.manga.mangacomics.adapters.out.persistence.entity;

import com.manga.mangacomics.domain.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private CredentialEntity credential;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        return userEntity;
    }

    public User toDomain() {
        User user = new User();

        user.setId(id);
        user.setEmail(email);
        user.setUsername(username);
        
        return user;
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
        this.id = id;
        this.username = username;
        this.email = email;
    }


    public long getId() {
        return id;
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
