package ru.otus.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.domain.mongo.mAuthor;
import ru.otus.domain.mongo.mBook;
import ru.otus.domain.mongo.mComment;
import ru.otus.domain.mongo.mGenre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private final List<mAuthor> authors = List.of(
            new mAuthor("Jack London"),
            new mAuthor("Gustave Flaubert"),
            new mAuthor("James Joyce"),
            new mAuthor("Emile Zola")
    );

    private final List<mGenre> genres = List.of(
            new mGenre("novel"),
            new mGenre("science fiction"),
            new mGenre("adventure fiction")
    );

    private List<mBook> books = List.of(
            new mBook("Ulysses", authors.subList(2, 3), genres.subList(0, 1)),
            new mBook("The Star Rover", authors.subList(0, 1), genres.subList(1, 2)),
            new mBook("Martin Iden", authors.subList(0, 1), genres.subList(0, 1)),
            new mBook("Madame Bovary", authors.subList(1, 2), genres.subList(0, 1))
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
        List<mComment> comments = List.of(
                new mComment("comment_1", books.get(0).getId()),
                new mComment("comment_2", books.get(0).getId()),
                new mComment("comment_3", books.get(1).getId()),
                new mComment("comment_4", books.get(2).getId())
        );
        repository.saveAll(comments);
    }
}
