package com.manga.mangacomics.adapter.out.persistence.jpa;

import org.springframework.stereotype.Component;

import com.manga.mangacomics.adapter.out.persistence.jpa.entity.CredentialEntity;
import com.manga.mangacomics.adapter.out.persistence.jpa.repository.CredentialRepository;
import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.exceptions.CredentialNotFoundException;
import com.manga.mangacomics.domain.port.out.persistence.CredentialRepositoryPort;

@Component
public class CredentialJpaPersistenceAdapter implements CredentialRepositoryPort {

    private final CredentialRepository credentialRepository;

    public CredentialJpaPersistenceAdapter(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public Credential getCredentialById(Long id) {
        CredentialEntity credentialEntity = credentialRepository.findById(id)
        .orElseThrow(() -> new CredentialNotFoundException("ID:" + id + "에 해당하는 Credential이 없습니다."));
        return credentialEntity.toDomain();
    }

    @Override
    public Credential save(Credential credential) {
        CredentialEntity credentialEntity = CredentialEntity.from(credential);
        return credentialRepository.save(credentialEntity).toDomain();
    }

}
