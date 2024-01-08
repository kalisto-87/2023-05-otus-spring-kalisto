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

    private List<Book> books = List.of(
            new Book("Ulysses", authors.subList(2, 3), genres.subList(0, 1)),
            new Book("The Star Rover", authors.subList(0, 1), genres.subList(1, 2)),
            new Book("Martin Iden", authors.subList(0, 1), genres.subList(0, 1)),
            new Book("Madame Bovary", authors.subList(1, 2), genres.subList(0, 1))
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
        repository.saveAll(authors).blockLast();
    }

    @ChangeSet(order = "004", id = "insertGenres", author = "Corrado Cattani", runAlways = true)
    public void insertGenres(GenreRepository repository) {
        repository.saveAll(genres).blockLast();
    }

    @ChangeSet(order = "005", id = "insertBooks", author = " Corrado Cattani", runAlways = true)
    public void insertBooks(BookRepository repository) {
        for (int i = 0; i < books.size(); i++) {
            Book res = repository.save(books.get(i)).block();
            books.get(i).setId(res.getId());
        }
    }

    @ChangeSet(order = "006", id = "commentBooks", author = " Corrado Cattani", runAlways = true)
    public void insertComments(CommentRepository repository) {
        List<Comment> comments = List.of(
                new Comment("comment_1", books.get(0).getId()),
                new Comment("comment_2", books.get(0).getId()),
                new Comment("comment_3", books.get(1).getId()),
                new Comment("comment_4", books.get(2).getId())
        );
        repository.saveAll(comments).blockLast();
    }
}
