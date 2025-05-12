package com.manga.mangacomics.domain.port.out.persistence;

import com.manga.mangacomics.adapter.out.persistence.jpa.entity.NovelEntity;
import com.manga.mangacomics.domain.entity.Novel;

public interface NovelRepositoryPort {

    Novel save(NovelEntity novel);
}
