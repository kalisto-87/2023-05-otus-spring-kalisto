package ru.otus.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.domain.Book;

@Component
public class BookConverter implements Converter<Book, String> {

    @Override
    public String convert(Book source) {
        return String.format("ID=%s; TITLE=%s; AUTHOR=%s; GENRE=%s",
                Long.toString(source.getId()), source.getName(),
                source.getAuthor().getName(), source.getGenre().getName());
    }
}
