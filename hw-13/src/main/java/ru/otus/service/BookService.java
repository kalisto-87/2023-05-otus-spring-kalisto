package ru.otus.service;

import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.dto.GenreDto;

import java.util.List;

public interface BookService {

    List<BookDto> findAll();

    List<BookDto> findByName(String bookName);

    BookDto findById(long bookId);

    List<BookDto> findByAuthorId(AuthorDto authorDto);

    List<BookDto> findByGenreId(GenreDto genreDto);

    BookDto insert(BookDto bookDto);

    BookDto update(BookDto bookDto);

    void delete(long bookId);
}
