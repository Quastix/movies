package be.vdab.movies.controllers;

import be.vdab.movies.services.FilmService;
import be.vdab.movies.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final FilmService filmService;

    public MandjeController(Mandje mandje, FilmService filmService) {
        this.mandje = mandje;
        this.filmService = filmService;
    }

    @PostMapping("{id}")
    public String voegToe(@PathVariable long id){
        mandje.voegToe(id);
        return "redirect:/mandje";
    }

    @GetMapping
    public ModelAndView toonMandjeO(){
        return new ModelAndView("mandje", "films",
                // Je leest in de database enkel de pizza’s die in het mandje liggen.
                // Dit is een performante aanpak.
                filmService.findByFilmIds(mandje.getIds()));
    }

    @PostMapping("verwijderen")
    public String delete(long[] id){
        mandje.delete(id);
        return "redirect:/mandje";
    }
}
