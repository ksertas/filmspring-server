package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.FilmService;
import nl.serkanertas.filmspringserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping()
    ResponseEntity<Object> getAllMovies() {
        return ResponseEntity.ok().body(filmService.getAllFilms());
    }

    @PutMapping("/{film_id}/{user_id}")
    ResponseEntity<Object> addFilmToWatched(@PathVariable("user_id") long user_id,
                                            @PathVariable("film_id") long film_id) {
        filmService.storeFilmToWatched(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{film_id}/{user_id}")
    ResponseEntity<Object> deleteFilmFromWatched(@PathVariable("user_id") long user_id,
                                                 @PathVariable("film_id") long film_id) {
        filmService.deleteFilmFromUser(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

}
