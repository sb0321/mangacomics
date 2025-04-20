package com.manga.mangacomics.adapters.out.persistence;

import org.springframework.stereotype.Component;

import com.manga.mangacomics.adapters.out.persistence.entity.NovelEntity;
import com.manga.mangacomics.adapters.out.persistence.repository.NovelRepository;
import com.manga.mangacomics.application.ports.out.persistence.NovelRepositoryPort;

@Component
public class NovelPersistenceAdapter implements NovelRepositoryPort {

    private final NovelRepository novelRepository;

    public NovelPersistenceAdapter(NovelRepository novelRepository) {
        this.novelRepository = novelRepository;
    }

    @Override
    public NovelEntity save(NovelEntity novel) {
        return novelRepository.save(novel);
    }

}
