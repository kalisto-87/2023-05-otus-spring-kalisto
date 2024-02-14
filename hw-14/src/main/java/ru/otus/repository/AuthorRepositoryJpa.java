package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.domain.h2.Author;

public interface AuthorRepositoryJpa extends JpaRepository<Author, String> {
    @Query(value = "select nextval('author_seq')", nativeQuery = true)
    Long getIdNextVal();
}
