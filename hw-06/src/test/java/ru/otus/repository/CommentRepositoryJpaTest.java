package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с сущностью Comment")
@DataJpaTest
@Import({CommentRepositoryJpa.class, BookRepositoryJpa.class})
public class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @Test
    public void checkGetCommentsByBook() {
        long bookId = 2L;
        List<Comment> comments = commentRepositoryJpa.getCommentsByBook(bookId);
        assertEquals(2, comments.size());
    }

    @Test
    public void checkInsertComment() {
        Book book = bookRepositoryJpa.findById(1L).orElse(null);
        Comment newComment = new Comment(0, "GOOD ONE", book);
        Comment insertedComment = commentRepositoryJpa.insert(newComment);
        Comment getComment = commentRepositoryJpa.findById(insertedComment.getId()).orElse(null);
        assertEquals(newComment, getComment);
    }

    @Test
    public void checkUpdateComment() {
        long commentId = 1L;
        String newComment = "New comment";
        Comment getComment = commentRepositoryJpa.findById(commentId).orElse(null);
        getComment.setText(newComment);
        commentRepositoryJpa.update(getComment);
        Comment updatedComment = commentRepositoryJpa.findById(commentId).orElse(null);
        assertEquals(newComment, updatedComment.getText());
    }

    @Test
    public void checkDeleteComment() {
        long commentId = 1L;
        Comment expectedComment = commentRepositoryJpa.findById(commentId).orElse(null);
        assertNotNull(expectedComment);
        commentRepositoryJpa.delete(expectedComment);
        expectedComment = commentRepositoryJpa.findById(commentId).orElse(null);
        assertNull(expectedComment);
    }
}
