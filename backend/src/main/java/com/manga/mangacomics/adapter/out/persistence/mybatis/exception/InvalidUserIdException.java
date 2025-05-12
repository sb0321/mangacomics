package com.manga.mangacomics.adapter.out.persistence.mybatis.exception;

public class InvalidUserIdException extends RuntimeException {

    public InvalidUserIdException(Long id) {
        super("Invalid user ID: " + id);
    }

}
