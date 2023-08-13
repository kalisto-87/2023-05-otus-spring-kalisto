package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbcImpl implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, name from GENRE", new GenreRowMapper());
    }

    @Override
    public List<Genre> findByName(String genreName) {
        return jdbc.query("select id, name from GENRE where lower(name) like '%' || :name || '%'",
                Map.of("name", genreName), new GenreRowMapper());
    }

    @Override
    public Genre findById(Long genreId) {
        return jdbc.queryForObject("select id, name from AUTHORS where id = :id",
                Map.of("id", genreId), new GenreRowMapper());
    }

    @Override
    public Genre insertGenre(String genreName) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genreName);
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into GENRE (name) VALUES(:name)", params, kh, new String[]{"id"});
        return new Genre(kh.getKey().longValue(), genreName);
    }

    @Override
    public boolean updateGenre(Long genreId, String genreName) {
        int cnt = jdbc.update("update GENRE set name = :name where id = :id",
                Map.of("id", genreId, "name", genreName));
        return cnt == 1;
    }

    @Override
    public boolean deleteGenre(Long genreId) {
        int cnt = jdbc.update("delete from GENRE where id = :id", Map.of("id", genreId));
        return cnt == 1;
    }

    private static class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getLong("id"), rs.getString("name"));
        }
    }
}
