package be.vdab.movies.controllers;

import be.vdab.movies.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("film")
public class FilmController {
    public FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("{id}")
    public ModelAndView findFilmById(@PathVariable long id) {
        var modelAndView = new ModelAndView("film");
        filmService.findFilmById(id).ifPresent(gevondenFilm ->
                modelAndView.addObject("film", gevondenFilm));
        filmService.findFilmById(id).ifPresent(gevondenFilm ->
                modelAndView.addObject("beschikbaar",
                        gevondenFilm.aantalBeschikbaar(gevondenFilm.getGereserveerd())));
        return modelAndView;
    }
}
