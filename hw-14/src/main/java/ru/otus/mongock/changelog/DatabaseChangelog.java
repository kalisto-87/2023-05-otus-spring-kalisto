package ru.otus.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.domain.mongo.MongoAuthor;
import ru.otus.domain.mongo.MongoBook;
import ru.otus.domain.mongo.MongoComment;
import ru.otus.domain.mongo.MongoGenre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private final List<MongoAuthor> authors = List.of(
            new MongoAuthor("Jack London"),
            new MongoAuthor("Gustave Flaubert"),
            new MongoAuthor("James Joyce"),
            new MongoAuthor("Emile Zola")
    );

    private final List<MongoGenre> genres = List.of(
            new MongoGenre("novel"),
            new MongoGenre("science fiction"),
            new MongoGenre("adventure fiction")
    );

    private List<MongoBook> books = List.of(
            new MongoBook("Ulysses", authors.subList(2, 3), genres.subList(0, 1)),
            new MongoBook("The Star Rover", authors.subList(0, 1), genres.subList(1, 2)),
            new MongoBook("Martin Iden", authors.subList(0, 1), genres.subList(0, 1)),
            new MongoBook("Madame Bovary", authors.subList(1, 2), genres.subList(0, 1))
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
        books = repository.saveAll(books);
    }

    @ChangeSet(order = "006", id = "commentBooks", author = " Corrado Cattani", runAlways = true)
    public void insertComments(CommentRepository repository) {
        List<MongoComment> comments = List.of(
                new MongoComment("comment_1", books.get(0).getId()),
                new MongoComment("comment_2", books.get(0).getId()),
                new MongoComment("comment_3", books.get(1).getId()),
                new MongoComment("comment_4", books.get(2).getId())
        );
        repository.saveAll(comments);
    }
}
