package ru.otus.domain.mongo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "comments")
@Document(collection = "books")
public class MongoBook {

    @Id
    private String id;

    private String title;

    private List<MongoAuthor> authors;

    private List<MongoGenre> genres;

    public MongoBook(String id, String title, List<MongoAuthor> authors, List<MongoGenre> genres) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.genres = genres;
    }

    public MongoBook(String title, List<MongoAuthor> authors, List<MongoGenre> genres) {
        this.title = title;
        this.authors = authors;
        this.genres = genres;
    }
}
