package ru.otus.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

@Component
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
    public Book update(Book book) {
        return em.merge(book);
    }

    @Override
    @Transactional
    public void delete(Book book) {
        em.remove(em.contains(book) ? book : em.merge(book));
    }
}
