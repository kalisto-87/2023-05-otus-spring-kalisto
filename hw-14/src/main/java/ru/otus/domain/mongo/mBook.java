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
public class mBook {

    @Id
    private String id;

    private String title;

    private List<mAuthor> authors;

    private List<mGenre> genres;

    public mBook(String id, String title, List<mAuthor> authors, List<mGenre> genres) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.genres = genres;
    }

    public mBook(String title, List<mAuthor> authors, List<mGenre> genres) {
        this.title = title;
        this.authors = authors;
        this.genres = genres;
    }
}
