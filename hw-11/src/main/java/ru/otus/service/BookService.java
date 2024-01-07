package ru.otus.service;

import ru.otus.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> findAll();

    List<BookDto> findByName(String bookName);

    BookDto findById(String bookId);

    /*List<BookDto> findByAuthorId(AuthorDto authorDto);

    List<BookDto> findByGenreId(GenreDto genreDto);*/

    BookDto insert(BookDto bookDto);

    BookDto update(BookDto bookDto);

    void delete(String bookId);
}
