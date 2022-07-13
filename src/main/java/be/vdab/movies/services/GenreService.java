package be.vdab.movies.services;

import be.vdab.movies.domain.Genre;
import be.vdab.movies.dto.FilmsPerGenre;
import be.vdab.movies.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAllGenres(){
        return genreRepository.findAllGenresAlfabetisch();
    }

    public List<FilmsPerGenre> findFilmsPerGenre(String naam){
        return genreRepository.findFilmsPerGenre(naam);
    }
}
