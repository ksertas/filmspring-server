package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping("/{film_id}")
    ResponseEntity<Object> getFilm(@PathVariable("film_id") long film_id) {
        return ResponseEntity.ok().body(filmService.getFilm(film_id));
    }

    @GetMapping()
    ResponseEntity<Object> getAllFilms() {
        return ResponseEntity.ok().body(filmService.getAllFilms());
    }

    @PutMapping("/watched/{film_id}/users/{user_id}")
    ResponseEntity<Object> addFilmToWatched(@PathVariable("user_id") long user_id,
                                            @PathVariable("film_id") long film_id) {
        filmService.storeFilmToWatched(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/watched/{film_id}/users/{user_id}")
    ResponseEntity<Object> deleteFilmFromWatched(@PathVariable("user_id") long user_id,
                                                 @PathVariable("film_id") long film_id) {
        filmService.deleteFilmFromWatched(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/planned/{film_id}/users/{user_id}")
    ResponseEntity<Object> addFilmToPlanned(@PathVariable("user_id") long user_id,
                                            @PathVariable("film_id") long film_id) {
        filmService.storeFilmToPlanned(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/planned/{film_id}/users/{user_id}")
    ResponseEntity<Object> deleteFilmFromPlanned(@PathVariable("user_id") long user_id,
                                                 @PathVariable("film_id") long film_id) {
        filmService.deleteFilmFromPlanned(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/planned/{film_id}/groups/{group_id}")
    ResponseEntity<Object> addFilmToPlannedGroup(@PathVariable("group_id") long group_id,
                                            @PathVariable("film_id") long film_id) {
        filmService.storeFilmToPlannedGroup(group_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/planned/{film_id}/groups/{group_id}")
    ResponseEntity<Object> deleteFilmFromPlannedGroup(@PathVariable("group_id") long group_id,
                                                 @PathVariable("film_id") long film_id) {
        filmService.deleteFilmFromPlannedGroup(group_id, film_id);
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
