package ru.otus.service;

import ru.otus.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAll();

    List<AuthorDto> findByName(String authorName);

    List<AuthorDto> findByIds(List<String> authorsId);

    AuthorDto findById(String authorId);

    AuthorDto insert(AuthorDto authorDto);

    AuthorDto update(AuthorDto authorDto);

    void delete(String authorId);
}
