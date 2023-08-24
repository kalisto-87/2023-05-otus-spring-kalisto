package ru.otus.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

@Repository
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
    public List<Author> findByIds(List<Long> ids) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.id in (:ids)", Author.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    @Override
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
    public Author update(Author author) {
        return em.merge(author);
    }

    @Override
    @Transactional
    public void delete(Author author) {
        em.remove(em.contains(author) ? author : em.merge(author));
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
