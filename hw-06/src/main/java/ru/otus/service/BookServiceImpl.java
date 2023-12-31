package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.BookRepository;

import java.util.List;

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
        return bookRepository.findByName(bookName);
    }

    @Override
    public Book findById(long bookId) {
        return bookRepository.findById(bookId).orElseThrow(
                () -> new DataNotFoundException(String.format("Book with id=%s not found", bookId)));
    }

    @Override
    @Transactional
    public Book insert(Book book) {
        return bookRepository.insert(book);
    }

    @Override
    @Transactional
    public Book update(long bookId, String bookName) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new DataNotFoundException(String.format("Book with id=%s not found", bookId)));
        book.setTitle(bookName);
        return bookRepository.update(book);
    }

    @Override
    @Transactional
    public void delete(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new DataNotFoundException(String.format("Book with id=%s not found", bookId)));
        bookRepository.delete(book);
    }
}
