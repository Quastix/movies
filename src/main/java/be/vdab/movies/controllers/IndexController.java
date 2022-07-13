package be.vdab.movies.controllers;

import be.vdab.movies.domain.Genre;
import be.vdab.movies.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    public GenreService genreService;

    public IndexController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index", "genres", genreService.findAllGenres());
    }
}
