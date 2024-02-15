package ru.otus.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.domain.Book;
import ru.otus.repository.BookRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HealthIndicator implements org.springframework.boot.actuate.health.HealthIndicator {

    private final BookRepository bookRepository;

    @Override
    public Health health() {

        List<Book> books = null;

        try {
            books = bookRepository.findAll();
        } catch (Exception e) {
            return Health.down().status(Status.DOWN)
                    .withDetail("message", "Ошибка при получении списка книг!")
                    .build();
        }

        if (books.isEmpty()) {
            return Health.down().status(Status.DOWN)
                    .withDetail("message", "Список книг пуст!")
                    .build();
        } else {
            return Health.up().withDetail("message", "Приложение работает нормально").build();
        }
    }

}
