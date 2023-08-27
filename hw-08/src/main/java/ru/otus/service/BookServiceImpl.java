package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByName(String bookName) {
        return bookRepository.findByTitleContainingIgnoreCase(bookName);
    }

    @Override
    public Optional<Book> findById(String bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public List<Book> findByAuthorId(String author) {
        return bookRepository.findBookByAuthorsIs(author);
    }

    @Override
    public List<Book> findByGenreId(String genre) {
        return bookRepository.findBookByGenresIs(genre);
    }

    @Override
    @Transactional
    public Book insert(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book update(String bookId, String bookName) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new DataNotFoundException(String.format("Book with id=%s not found", bookId)));
        book.setTitle(bookName);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(String bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new DataNotFoundException(String.format("Book with id=%s not found", bookId)));
        bookRepository.delete(book);
    }
}
