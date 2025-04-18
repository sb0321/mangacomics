package com.manga.mangacomics.application.ports.out.persistence;

import com.manga.mangacomics.adapters.out.persistence.entity.NovelEntity;

public interface NovelRepositoryPort {

    NovelEntity save(NovelEntity novel);
}
