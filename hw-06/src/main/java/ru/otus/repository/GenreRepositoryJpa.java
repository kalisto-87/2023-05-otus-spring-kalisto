package ru.otus.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select a from Genre a", Genre.class);
        return query.getResultList();
    }

    @Override
    public Optional<Genre> findById(long genreId) {
        return Optional.ofNullable(em.find(Genre.class, genreId));
    }

    @Override
    public List<Genre> findByIds(List<Long> ids) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.id in (:ids)", Genre.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    @Override
    public List<Genre> findByName(String genreName) {
        TypedQuery<Genre> query = em.createQuery("""
                select a
                from Genre a
                where lower(a.name) like lower(concat('%', :name, '%'))
                """, Genre.class);
        query.setParameter("name", genreName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Genre insert(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    @Transactional
    public Genre update(Genre genre) {
        return em.merge(genre);
    }

    @Override
    @Transactional
    public void delete(Genre genre) {
        em.remove(em.contains(genre) ? genre : em.merge(genre));
    }

    @Override
    public List<Genre> findByBook(long bookId) {
        TypedQuery<Genre> query = em.createQuery(
                """
                        select g from Book b
                        join b.genres g
                        where b.id = :bookId
                        """,
                Genre.class
        );
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }
}
