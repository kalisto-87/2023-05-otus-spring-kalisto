package ru.otus.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;
import ru.otus.exception.DataNotFoundException;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findById(long authorId) {
        return Optional.ofNullable(em.find(Author.class, authorId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findByIds(List<Long> ids) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.id in (:ids)", Author.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findByName(String authorName) {
        TypedQuery<Author> query = em.createQuery("""
                select a 
                from Author a
                where lower(a.name) like lower(concat('%', :name, '%'))
                """, Author.class);
        query.setParameter("name", authorName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Author insert(Author author) {
        if (author.getId() == 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    @Transactional
    public boolean update(Author author) {
        if (em.find(Author.class, author.getId()) != null) {
            return em.merge(author) != null;
        } else {
            throw new DataNotFoundException(String.format("Author with id = %s not found", author.getId()));
        }
    }

    @Override
    @Transactional
    public boolean delete(long authorId) {
        Author author = em.find(Author.class, authorId);
        if (author != null) {
            em.remove(author);
            return true;
        } else {
            throw new DataNotFoundException(String.format("Author with id = %s not found", authorId));
        }
    }

    @Override
    public List<Author> findByBook(long bookId) {
        TypedQuery<Author> query = em.createQuery(
                """
                select a from Book b
                join b.authors a
                where b.id in (:bookId)
                """,
                Author.class
        );
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }
}
