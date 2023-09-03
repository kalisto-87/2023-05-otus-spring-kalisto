package ru.otus.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.domain.Genre;

@Component
public class GenreConverter implements Converter<Genre, String> {

    @Override
    public String convert(Genre source) {
        return String.format("ID=%s; NAME=%s", Long.toString(source.getId()), source.getName());
    }
}
