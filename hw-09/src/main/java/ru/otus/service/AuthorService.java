package ru.otus.service;

import ru.otus.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAll();

    List<AuthorDto> findByName(String authorName);

    List<AuthorDto> findByIds(List<Long> authorsId);

    AuthorDto findById(long authorId);

    AuthorDto insert(AuthorDto authorDto);

    AuthorDto update(AuthorDto authorDto);

    void delete(long authorId);
}
