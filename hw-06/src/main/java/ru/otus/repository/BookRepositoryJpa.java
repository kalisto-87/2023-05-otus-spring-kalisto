package ru.otus.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Book> findAll() {
        List<Book> books = em.createQuery("select a from Book a", Book.class).getResultList();
        books.forEach(book -> {
            Hibernate.initialize(book.getAuthors());
            Hibernate.initialize(book.getGenres());
        });
        return books;
    }

    @Override
    public Optional<Book> findById(long bookId) {
        return Optional.empty();
    }

    @Override
    public List<Book> findByName(String bookName) {
        return null;
    }

    @Override
    public Book insert(Book book) {
        return null;
    }

    @Override
    public boolean update(Book book) {
        return false;
    }

    @Override
    public boolean delete(long bookId) {
        return false;
    }
}
