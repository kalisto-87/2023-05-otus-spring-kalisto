package ru.otus.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.domain.Book;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookConverter implements Converter<Book, String> {

    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

    @Override
    public String convert(Book source) {
        return String.format("ID=%s; TITLE=%s; AUTHOR=%s; GENRE=%s",
                source.getId(), source.getTitle(),
                source.getAuthors().stream().map(author -> author.getName()).
                        collect(Collectors.joining(",")),
                source.getGenres().stream().map(genre -> genre.getName()).
                        collect(Collectors.joining(",")));
    }
}
