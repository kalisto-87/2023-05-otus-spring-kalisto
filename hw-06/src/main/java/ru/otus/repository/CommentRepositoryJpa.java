package ru.otus.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Comment;
import ru.otus.exception.DataNotFoundException;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBook(long bookId) {
        TypedQuery<Comment> query = em.createQuery("""
                select c
                from Comment c
                join fetch c.book
                where c.book.id = :bookId
                """, Comment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    @Override
    public Optional<Comment> findById(long commentId) {
        return Optional.ofNullable(em.find(Comment.class, commentId));
    }

    @Override
    @Transactional
    public Comment insert(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    @Transactional
    public boolean update(Comment comment) {
        if (em.find(Comment.class, comment.getId()) != null) {
            return em.merge(comment) != null;
        } else {
            throw new DataNotFoundException(String.format("Comment with id = %s not found", comment.getId()));
        }
    }

    @Override
    @Transactional
    public boolean delete(long commentId) {
        Comment comment = em.find(Comment.class, commentId);
        if (comment != null) {
            em.remove(comment);
            return true;
        } else {
            throw new DataNotFoundException(String.format("Comment with id = %s not found", commentId));
        }
    }
}
