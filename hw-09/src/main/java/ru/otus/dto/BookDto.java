package ru.otus.dto;

import lombok.*;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class BookDto {
    private long id;

    private String title;

    private List<Author> authors;

    private List<Genre> genres;

    public Book toDomainObject(){
        return new Book(id, title, authors, genres);
    }

    public static BookDto fromDomainObject(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthors(), book.getGenres());
    }

}
