package com.manga.mangacomics.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manga.mangacomics.adapters.out.persistence.entity.CredentialEntity;

@Repository
public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {

}
