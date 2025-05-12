package com.manga.mangacomics.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manga.mangacomics.adapter.out.persistence.jpa.entity.CredentialEntity;

@Repository
public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {

}
