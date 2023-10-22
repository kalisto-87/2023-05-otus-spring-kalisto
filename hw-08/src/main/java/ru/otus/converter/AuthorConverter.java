package ru.otus.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.domain.Author;

@Component
public class AuthorConverter implements Converter<Author, String> {

    @Override
    public String convert(Author source) {
        return String.format("ID=%s; NAME=%s", source.getId(), source.getName());
    }
}
