package ru.otus.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.domain.Comment;
import org.springframework.core.convert.converter.Converter;

@Component
@RequiredArgsConstructor
public class CommentConverter implements Converter<Comment, String> {

    private final BookConverter bookConverter;

    @Override
    public String convert(Comment source) {
        return String.format("ID=%s; TEXT=%s for BOOK=%s", source.getId(), source.getText(),
                source.getBook().getTitle());
    }
}
