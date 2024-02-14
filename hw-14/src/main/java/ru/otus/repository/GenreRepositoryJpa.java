package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.domain.h2.Genre;

public interface GenreRepositoryJpa extends JpaRepository<Genre, String> {
    @Query(value = "select nextval('genre_seq')", nativeQuery = true)
    Long getIdNextVal();
}
