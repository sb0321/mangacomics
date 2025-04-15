package com.manga.mangacomics.adapters.out.web.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manga.mangacomics.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
