package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.otus.domain.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {
}
