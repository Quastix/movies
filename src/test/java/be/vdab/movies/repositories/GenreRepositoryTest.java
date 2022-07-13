package be.vdab.movies.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;


@JdbcTest
@Import(GenreRepository.class)
@Sql("/insertGenres.sql")
public class GenreRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    public static final String GENRES = "genres";

    private final GenreRepository repository;

    public GenreRepositoryTest(GenreRepository repository) {
        this.repository = repository;
    }

    @Test
    void findAllGenresAlfabetisch(){
        assertThat(repository.findAllGenresAlfabetisch())
                .hasSize(countRowsInTable(GENRES));
    }
}
