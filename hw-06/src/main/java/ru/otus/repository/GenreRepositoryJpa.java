package ru.otus.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Genre;
import ru.otus.exception.DataNotFoundException;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select a from Genre a", Genre.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> findById(long genreId) {
        return Optional.ofNullable(em.find(Genre.class, genreId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findByName(String genreName) {
        TypedQuery<Genre> query = em.createQuery("select a " +
                "from Genre a " +
                "where lower(a.name) like lower(concat('%', :name, '%'))", Genre.class);
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
    public boolean update(Genre genre) {
        if (em.find(Genre.class, genre.getId()) != null) {
            return em.merge(genre) != null;
        } else {
            throw new DataNotFoundException(String.format("Genre with id = %s not found", genre.getId()));
        }
    }

    @Override
    @Transactional
    public boolean delete(long genreId) {
        Genre genre = em.find(Genre.class, genreId);
        if (genre != null) {
            em.remove(genre);
            return true;
        } else {
            throw new DataNotFoundException(String.format("Genre with id = %s not found", genreId));
        }
    }
}
