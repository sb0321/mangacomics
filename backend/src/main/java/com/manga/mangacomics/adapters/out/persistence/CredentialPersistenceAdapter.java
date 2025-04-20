package com.manga.mangacomics.adapters.out.persistence;

import org.springframework.stereotype.Component;

import com.manga.mangacomics.adapters.out.persistence.entity.CredentialEntity;
import com.manga.mangacomics.adapters.out.persistence.repository.CredentialRepository;
import com.manga.mangacomics.application.ports.out.persistence.CredentialRepositoryPort;
import com.manga.mangacomics.domain.entity.Credential;
import com.manga.mangacomics.domain.exceptions.CredentialNotFoundException;

@Component
public class CredentialPersistenceAdapter implements CredentialRepositoryPort {

    private final CredentialRepository credentialRepository;

    public CredentialPersistenceAdapter(CredentialRepository credentialRepository) {
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
