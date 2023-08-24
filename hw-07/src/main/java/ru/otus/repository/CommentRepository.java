package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Transactional
    void deleteByBook(Book book);
}
