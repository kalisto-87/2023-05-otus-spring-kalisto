package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookDao;
import ru.otus.domain.Book;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServicesImpl implements BookService {

    private final BookDao dao;

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Override
    public List<Book> findByName(String bookName) {
        return dao.findByName(bookName);
    }

    @Override
    public Book insert(String bookName, Long authorId, Long genreId) {
        return dao.insertBook(bookName, authorId, genreId);
    }

    @Override
    public boolean deleteBook(Long bookId) {
        return dao.deleteBook(bookId);
    }
}
