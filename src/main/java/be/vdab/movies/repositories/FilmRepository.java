package be.vdab.movies.repositories;

import be.vdab.movies.domain.Film;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class FilmRepository {

private final JdbcTemplate template;

    public FilmRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Film> filmMapper =
            (result, rowNum) ->
                    new Film(result.getLong("id"),
                            result.getLong("genreId"),
                            result.getString("titel"),
                            result.getBigDecimal("voorraad"),
                            result.getBigDecimal("gereserveerd"),
                            result.getBigDecimal("prijs"));

    public Optional<Film> findById(long id) {
        try {
            var sql = """
                    select id, genreId, titel, voorraad, gereserveerd, prijs
                    from films
                    where id = ?
                    """;
            return Optional.of(template.queryForObject(sql, filmMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex){
            return Optional.empty();
        }
    }

    public List<Film> findByFilmIds(Set<Long> ids) {
        if (ids.isEmpty()) {
            return List.of();
        }
        var sql = """
                select id, genreId, titel, voorraad, gereserveerd, prijs
                from films
                where id in (
                """
                + "?,".repeat(ids.size() - 1)
                + "?) order by id";
        return template.query(sql, filmMapper, ids.toArray());
    }

}
