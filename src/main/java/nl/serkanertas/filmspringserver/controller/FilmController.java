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

    @GetMapping()
    ResponseEntity<Object> getAllMovies() {
        return ResponseEntity.ok().body(filmService.getAllFilms());
    }

    @PutMapping("/{film_id}/users/{user_id}")
    ResponseEntity<Object> addFilmToWatched(@PathVariable("user_id") long user_id,
                                            @PathVariable("film_id") long film_id) {
        filmService.storeFilmToWatched(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{film_id}/users/{user_id}")
    ResponseEntity<Object> deleteFilmFromWatched(@PathVariable("user_id") long user_id,
                                                 @PathVariable("film_id") long film_id) {
        filmService.deleteFilmFromUser(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{film_id}/actors")
    ResponseEntity<Object> getAllActorsFromFilm(@PathVariable("film_id") long film_id) {
        return ResponseEntity.ok().body(filmService.getAllActorsFromFilm(film_id));
    }

    @PutMapping("/{film_id}/actors/{actor_id}")
    ResponseEntity<Object> storeActorToFilm(@PathVariable("actor_id") long actor_id, @PathVariable("film_id") long film_id) {
        filmService.storeActorToFilm(actor_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{film_id}/actors/{actor_id}")
    ResponseEntity<Object> deleteActorFromFilm(@PathVariable("actor_id") long actor_id,
                                               @PathVariable("film_id") long film_id) {
        filmService.deleteActorFromFilm(actor_id, film_id);
        return ResponseEntity.noContent().build();
    }
}
