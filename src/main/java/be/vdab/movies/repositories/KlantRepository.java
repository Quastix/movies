package be.vdab.movies.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KlantRepository {

    private final JdbcTemplate template;

    public KlantRepository(JdbcTemplate template) {
        this.template = template;
    }
}
