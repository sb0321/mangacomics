package com.manga.mangacomics.adapter.out.persistence.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.manga.mangacomics.adapter.out.persistence.jpa.entity.NovelEntity;
import com.manga.mangacomics.adapter.out.persistence.jpa.repository.NovelRepository;
import com.manga.mangacomics.domain.entity.Novel;

public class NovelJpaPersistenceAdapterTest {

    private NovelRepository novelRepository;
    private NovelJpaPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        novelRepository = mock(NovelRepository.class);
        adapter = new NovelJpaPersistenceAdapter(novelRepository);
    }

    @Test
    void save_Novel_성공_테스트() {
        NovelEntity novelEntity = mock(NovelEntity.class);
        Novel savedNovel = mock(Novel.class);

        when(novelRepository.save(any(NovelEntity.class))).thenReturn(novelEntity);
        when(novelEntity.toDomain()).thenReturn(savedNovel);

        Novel result = adapter.save(novelEntity);

        assertEquals(savedNovel, result);
        verify(novelRepository).save(novelEntity);
        verify(novelEntity).toDomain();
    }

    @Test
    void save_Novel_실패_테스트() {
        NovelEntity novelEntity = mock(NovelEntity.class);

        when(novelRepository.save(novelEntity)).thenThrow(new RuntimeException());

        try {
            adapter.save(novelEntity);
            fail();
        } catch (RuntimeException e) {
            verify(novelRepository).save(novelEntity);
        }
    }
}