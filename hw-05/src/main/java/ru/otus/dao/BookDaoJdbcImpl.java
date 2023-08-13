package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbcImpl implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book findByName(String name) {
        return null;
    }

    @Override
    public void insertBook(String name) {

    }

    @Override
    public void updateBook(Long bookId, String name) {

    }

    @Override
    public void deleteBook(Long bookId) {

    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return null;
        }
    }
}
