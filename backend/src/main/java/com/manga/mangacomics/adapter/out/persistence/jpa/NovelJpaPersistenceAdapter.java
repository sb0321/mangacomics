package com.manga.mangacomics.adapter.out.persistence.jpa;

import org.springframework.stereotype.Component;

import com.manga.mangacomics.adapter.out.persistence.jpa.entity.NovelEntity;
import com.manga.mangacomics.adapter.out.persistence.jpa.repository.NovelRepository;
import com.manga.mangacomics.domain.entity.Novel;
import com.manga.mangacomics.domain.port.out.persistence.NovelRepositoryPort;

@Component
public class NovelJpaPersistenceAdapter implements NovelRepositoryPort {

    private final NovelRepository novelRepository;

    public NovelJpaPersistenceAdapter(NovelRepository novelRepository) {
        this.novelRepository = novelRepository;
    }

    @Override
    public Novel save(NovelEntity novel) {
        return novelRepository.save(novel).toDomain();
    }

}
