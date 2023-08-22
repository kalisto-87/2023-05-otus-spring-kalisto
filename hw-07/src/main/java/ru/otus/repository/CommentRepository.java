package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
