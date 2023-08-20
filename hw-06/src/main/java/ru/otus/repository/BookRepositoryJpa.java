package ru.otus.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

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
        return books;
    }

    @Override
    public Optional<Book> findById(long bookId) {
        return Optional.ofNullable(em.find(Book.class, bookId));
    }

    @Override
    public List<Book> findByName(String bookName) {
        TypedQuery<Book> query = em.createQuery("""
                select a 
                from Book a
                where lower(a.title) like lower(concat('%', :name, '%'))
                """, Book.class);
        query.setParameter("name", bookName);
        return query.getResultList();
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
