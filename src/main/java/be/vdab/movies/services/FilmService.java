package be.vdab.movies.services;

import be.vdab.movies.Exceptions.FindFilmByIdException;
import be.vdab.movies.domain.Film;
import be.vdab.movies.repositories.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Optional<Film> findFilmById(long id) {
        try {
            return filmRepository.findById(id);
        } catch (FindFilmByIdException ex) {
            return Optional.empty();
        }
    }

    public List<Film> findByFilmIds(Set<Long> ids) {
        return filmRepository.findByFilmIds(ids);
    }
}
