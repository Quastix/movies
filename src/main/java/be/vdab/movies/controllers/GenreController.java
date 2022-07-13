package be.vdab.movies.controllers;

import be.vdab.movies.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("genre")
public class GenreController {

    public GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("{naam}")
    public ModelAndView filmsPerGenre(@PathVariable String naam){
        var modelAndView = new ModelAndView("genre"
                , "filmsPerGenre"
                , genreService.findFilmsPerGenre(naam));
        modelAndView.addObject("genres"
                , genreService.findAllGenres());
        return modelAndView;
    }
}
