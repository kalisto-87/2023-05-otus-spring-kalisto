package ru.otus.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;
import ru.otus.exception.DataNotFoundException;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> findById(long authorId) {
        return Optional.ofNullable(em.find(Author.class, authorId));
    }

    @Override
    public List<Author> findByName(String authorName) {
        TypedQuery<Author> query = em.createQuery("select a " +
                "from Author a" +
                "where a.name = :name", Author.class);
        query.setParameter("name", authorName);
        return query.getResultList();
    }

    @Override
    public Author insert(Author author) {
        if (author.getId() == 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public boolean update(long authorId, String authorName) {
        Author author = em.find(Author.class, authorId);
        if (author != null) {
            author.setName(authorName);
            return em.merge(author) != null;
        } else {
            throw new DataNotFoundException(String.format("Author with id = %s not found",
                    authorId));
        }
    }

    @Override
    public boolean delete(long authorId) {
        Author author = em.find(Author.class, authorId);
        if (author != null) {
            em.remove(author);
            return true;
        } else {
            throw new DataNotFoundException(String.format("Author with id = %s not found",
                    authorId));
        }
    }
}
