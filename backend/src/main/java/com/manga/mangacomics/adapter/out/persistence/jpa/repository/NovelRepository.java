package com.manga.mangacomics.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manga.mangacomics.adapter.out.persistence.jpa.entity.NovelEntity;

public interface NovelRepository extends JpaRepository<NovelEntity, Long> {
}
