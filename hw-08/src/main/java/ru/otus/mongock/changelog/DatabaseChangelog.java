package ru.otus.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private final List<Author> authors = List.of(
            new Author("Jack London"),
            new Author("Gustave Flaubert"),
            new Author("James Joyce"),
            new Author("Emile Zola")
    );

    private final List<Genre> genres = List.of(
            new Genre("novel"),
            new Genre("science fiction"),
            new Genre("adventure fiction")
    );

    private final List<Book> books = List.of(
            new Book("1", "Ulysses", authors.subList(2, 3), genres.subList(0, 1)),
            new Book("2", "The Star Rover", authors.subList(0, 1), genres.subList(1, 2)),
            new Book("3", "Martin Iden", authors.subList(0, 1), genres.subList(0, 1)),
            new Book("4", "Madame Bovary", authors.subList(1, 2), genres.subList(0, 1))
    );

    private final List<Comment> comments = List.of(
            new Comment("comment_1", "1"),
            new Comment("comment_2", "1"),
            new Comment("comment_3", "2"),
            new Comment("comment_4", "3")
    );

    @ChangeSet(order = "001", id = "DropDb", author = "Corrado Cattani", runAlways = true)
    public void dropDb(MongoDatabase mdb) {
        mdb.drop();
    }

    @ChangeSet(order = "002", id = "CreateCollections", author = "Corrado Cattani", runAlways = true)
    public void createCollections(MongoDatabase mdb) {
        mdb.createCollection("authors");
        mdb.createCollection("genre");
        mdb.createCollection("books");
        mdb.createCollection("comments");
    }

    @ChangeSet(order = "003", id = "insertAuthors", author = "Corrado Cattani", runAlways = true)
    public void insertAuthors(AuthorRepository repository) {
        repository.saveAll(authors);
    }

    @ChangeSet(order = "004", id = "insertGenres", author = "Corrado Cattani", runAlways = true)
    public void insertGenres(GenreRepository repository) {
        repository.saveAll(genres);
    }

    @ChangeSet(order = "005", id = "insertBooks", author = " Corrado Cattani", runAlways = true)
    public void insertBooks(BookRepository repository) {
        repository.saveAll(books);
    }

    @ChangeSet(order = "006", id = "commentBooks", author = " Corrado Cattani", runAlways = true)
    public void insertComments(CommentRepository repository) {
        repository.saveAll(comments);
    }
}
