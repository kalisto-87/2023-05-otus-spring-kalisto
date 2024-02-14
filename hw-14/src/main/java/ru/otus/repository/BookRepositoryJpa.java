package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.domain.h2.Book;

public interface BookRepositoryJpa extends JpaRepository<Book, String> {
    @Query(value = "select nextval('book_seq')", nativeQuery = true)
    Long getIdNextVal();
}
