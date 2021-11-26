package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.FilmService;
import nl.serkanertas.filmspringserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    ResponseEntity<Object> getAllMovies() {
        return ResponseEntity.ok().body(filmService.getAllFilms());
    }


    @PutMapping("/add/film/{user_id}/{film_id}")
    ResponseEntity<Object> addFilmToWatched(@PathVariable("user_id") long user_id, @PathVariable("film_id") long film_id) {
        filmService.addFilmToWatched(user_id, film_id);
        return ResponseEntity.noContent().build();
    }


}
