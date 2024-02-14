package ru.otus.domain.h2;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "comments")
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    parameters = {
            @Parameter(name = "sequence_name", value = "book_seq"),
            @Parameter(name = "initial_value", value = "1"),
            @Parameter(name = "increment_size", value = "1")
    })
    private long id;

    @Column(name = "name", nullable = false)
    private String title;

    @Fetch(FetchMode.JOIN)
    @BatchSize(size = 5)
    @ManyToMany(targetEntity = Author.class, fetch = FetchType.EAGER)
    @JoinTable(name = "author_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @Fetch(FetchMode.JOIN)
    @BatchSize(size = 5)
    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.EAGER)
    @JoinTable(name = "genre_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "book", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> comments;

    public Book(long id, String title, List<Author> authors, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.genres = genres;
    }
}
