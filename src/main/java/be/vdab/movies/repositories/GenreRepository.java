package be.vdab.movies.repositories;

import be.vdab.movies.domain.Genre;
import be.vdab.movies.dto.FilmsPerGenre;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepository {

    private final JdbcTemplate template;

    public GenreRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Genre> genreMapper =
            (result, rowNum) ->
                    new Genre(result.getLong("id"),
                            result.getString("naam"));

    public List<Genre> findAllGenresAlfabetisch() {
        var sql = """
                select id, naam
                from genres
                order by naam
                """;
        return template.query(sql, genreMapper);
    }

    public List<FilmsPerGenre> findFilmsPerGenre(String naam) {
        var sql = """
                select genres.id, genres.naam, films.id as filmId
                from genres
                left outer join films
                on genres.id = films.genreId
                where genres.naam = ?
                order by naam
                """;
        RowMapper<FilmsPerGenre> mapper = (result, rowNum) ->
                new FilmsPerGenre(
                        result.getLong("id"),
                        result.getString("naam"),
                        result.getLong("filmId"));
        return template.query(sql, mapper, naam);
    }
}
