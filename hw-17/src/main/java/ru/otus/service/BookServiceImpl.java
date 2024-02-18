package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.dto.GenreDto;
import ru.otus.exception.DataNotFoundException;
import ru.otus.mapper.AuthorMapper;
import ru.otus.mapper.BookMapper;
import ru.otus.mapper.GenreMapper;
import ru.otus.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final AuthorMapper authorMapper;

    private final GenreMapper genreMapper;

    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDtoList(books);
    }

    @Override
    public List<BookDto> findByName(String bookName) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(bookName);
        return bookMapper.toDtoList(books);
    }

    @Override
    public BookDto findById(long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new DataNotFoundException(String.format("Book with id=%s", bookId)));
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> findByAuthorId(AuthorDto authorDto) {
        List<Book> books = bookRepository.findByAuthors(authorMapper.toDomain(authorDto));
        return bookMapper.toDtoList(books);
    }

    @Override
    public List<BookDto> findByGenreId(GenreDto genreDto) {
        List<Book> books = bookRepository.findByGenres(genreMapper.toDomain(genreDto));
        return bookMapper.toDtoList(books);
    }

    @Override
    @Transactional
    public BookDto insert(BookDto bookDto) {
        Book newBook = bookMapper.toDomain(bookDto);
        if (bookDto.getAuthors() == null || bookDto.getAuthors().size() == 0) {
            throw new DataNotFoundException("List of authors is empty");
        }
        if (bookDto.getGenres() == null || bookDto.getGenres().size() == 0) {
            throw new DataNotFoundException("List of genres is empty");
        }
        bookRepository.save(newBook);
        return bookMapper.toDto(newBook);
    }

    @Override
    @Transactional
    public BookDto update(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getId()).orElseThrow(
                () -> new DataNotFoundException(String.format("Book with id=%s not found", bookDto.getId())));
        String title = bookDto.getTitle();
        if (title != null && !title.isEmpty()) {
            book.setTitle(title);
        }

        if (book.getAuthors() == null || book.getAuthors().size() == 0) {
            throw new DataNotFoundException("List of authors is empty");
        }

        if (book.getGenres() == null || book.getGenres().size() == 0) {
            throw new DataNotFoundException("List of genres is empty");
        }

        book.setAuthors(bookDto.getAuthors());
        book.setGenres(bookDto.getGenres());
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    @Transactional
    public void delete(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new DataNotFoundException(String.format("Book with id=%s not found", bookId)));
        bookRepository.delete(book);
    }
}
