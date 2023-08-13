package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbcImpl implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, name from AUTHORS", new AuthorRowMapper());
    }

    @Override
    public List<Author> findByName(String authorName) {
        return jdbc.query("select id, name from AUTHORS where lower(name) like lower('%' || :name || '%')",
                Map.of("name", authorName), new AuthorRowMapper());
    }

    @Override
    public Author findById(Long authorId) {
        return jdbc.queryForObject("select id, name from AUTHORS where id = :id",
                Map.of("id", authorId), new AuthorRowMapper());
    }

    @Override
    public Author insertAuthor(String authorName) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", authorName);
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into AUTHORS (name) VALUES(:name)", params, kh, new String[]{"id"});
        return new Author(kh.getKey().longValue(), authorName);
    }

    @Override
    public boolean updateAuthor(Long authorId, String authorName) {
        int cnt = jdbc.update("update AUTHORS set name = :name where id = :id",
                Map.of("id", authorId, "name", authorName));
        return cnt == 1;
    }

    @Override
    public boolean deleteAuthor(Long authorId) {
        int cnt = jdbc.update("delete from AUTHORS where id = :id", Map.of("id", authorId));
        return cnt == 1;
    }

    private static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int numRow) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("name"));
        }
    }
}
