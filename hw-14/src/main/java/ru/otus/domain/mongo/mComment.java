package ru.otus.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "comments")
public class mComment {

    @Id
    private String id;

    private String text;

    private String bookId;

    public mComment(String text, String bookId) {
        this.text = text;
        this.bookId = bookId;
    }
}
