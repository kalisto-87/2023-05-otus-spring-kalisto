package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.dao.exception.DataNotFoundException;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbcImpl implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    @Override
    public List<Book> getAll() {
        return jdbc.query("select b.id, b.name, " +
                        "a.id as author_id, a.name as author_name, " +
                        "g.id as genre_id, g.name as genre_name " +
                        "from BOOKS b " +
                        "join AUTHORS a " +
                        "on b.author_id = a.id " +
                        "join GENRE g " +
                        "on b.genre_id = g.id",
                new BookRowMapper());
    }

    @Override
    public List<Book> findByName(String name) {
        return jdbc.query("select b.id, b.name, " +
                        "a.id as author_id, a.name as author_name, " +
                        "g.id as genre_id, g.name as genre_name " +
                        "from BOOKS b " +
                        "join AUTHORS a " +
                        "on b.author_id = a.id " +
                        "join GENRE g " +
                        "on b.genre_id = g.id " +
                        "where lower(b.name) like '%' || :name || '%'",
                Map.of("name", name),
                new BookRowMapper());
    }

    @Override
    public Book findById(Long bookId) {
        return jdbc.queryForObject("select b.id, b.name, " +
                        "a.id as author_id, a.name as author_name, " +
                        "g.id as genre_id, g.name as genre_name " +
                        "from BOOKS b " +
                        "join AUTHORS a " +
                        "on b.author_id = a.id " +
                        "join GENRE g " +
                        "on b.genre_id = g.id " +
                        "where b.id = :id",
                Map.of("id", bookId),
                new BookRowMapper());
    }

    @Override
    public Book insertBook(String bookName, Long authorId, Long genreId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", bookName);
        params.addValue("author_id", authorId);
        params.addValue("genre_id", genreId);
        KeyHolder kh = new GeneratedKeyHolder();
        Author author = authorDao.findById(authorId).
                orElseThrow(() -> new DataNotFoundException(String.format("Author with id =%s not found!", authorId)));
        Genre genre = genreDao.findById(genreId).
                orElseThrow(() -> new DataNotFoundException(String.format("Genre with id = %s not found!", genreId)));
        jdbc.update("insert into books (name, author_id, genre_id) " +
                "values(:name, :author_id, :genre_id)", params, kh, new String[]{"id"});
        return new Book(kh.getKey().longValue(), bookName, author, genre);
    }

    @Override
    public boolean deleteBook(Long bookId) {
        int cnt = jdbc.update("delete from BOOKS where id = :id", Map.of("id", bookId));
        return cnt == 1;
    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author(rs.getLong("author_id"),
                    rs.getString("author_name"));
            Genre genre = new Genre(rs.getLong("id"),
                    rs.getString("genre_name"));
            return new Book(rs.getLong("id"), rs.getString("name"),
                    author, genre);
        }
    }
}
