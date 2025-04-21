package com.manga.mangacomics.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manga.mangacomics.adapters.out.persistence.entity.NovelEntity;

public interface NovelRepository extends JpaRepository<NovelEntity, Long> {
}
