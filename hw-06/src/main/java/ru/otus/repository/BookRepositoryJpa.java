package ru.otus.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;
import ru.otus.exception.DataNotFoundException;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        List<Book> books = em.createQuery("""
                select a from Book a
                left join fetch a.authors
                """, Book.class).getResultList();
        return books;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(long bookId) {
        return Optional.ofNullable(em.find(Book.class, bookId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByName(String bookName) {
        TypedQuery<Book> query = em.createQuery("""
                select a 
                from Book a
                left join fetch a.authors
                where lower(a.title) like lower(concat('%', :name, '%'))
                """, Book.class);
        query.setParameter("name", bookName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Book insert(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    @Transactional
    public boolean update(Book book) {
        if (em.find(Book.class, book.getId()) != null) {
            return em.merge(book) != null;
        } else {
            throw new DataNotFoundException(String.format("Book with id = %s not found", book.getId()));
        }
    }

    @Override
    @Transactional
    public boolean delete(long bookId) {
        Book book = em.find(Book.class, bookId);
        if (book != null) {
            em.remove(book);
            return true;
        } else {
            throw new DataNotFoundException(String.format("Book with id = %s not found", bookId));
        }
    }
}
