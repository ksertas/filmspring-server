package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.StoreActorService;
import nl.serkanertas.filmspringserver.service.models.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService filmService;
    private final StoreActorService storeActorService;

    public FilmController(FilmService filmService,
                          StoreActorService storeActorService) {
        this.filmService = filmService;
        this.storeActorService = storeActorService;
    }

    @GetMapping("/{film_id}")
    @PreAuthorize("hasRole(\"ROLE_USER\")")
    ResponseEntity<Object> getSearchedFilm(@PathVariable("film_id") long film_id) {
        return ResponseEntity.ok().body(filmService.getSearchedFilm(film_id));
    }

    @GetMapping
    @PreAuthorize("hasRole(\"ROLE_USER\")")
    ResponseEntity<Object> getSearchedFilms(@RequestParam("search") String query) {
        return ResponseEntity.ok().body(filmService.getSearchedFilms(query));
    }

    @GetMapping("/raw")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> getAllFilms() {
        return ResponseEntity.ok().body(filmService.getAllFilmEntities());
    }

    @GetMapping("/raw/{film_id}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> getFilmEntity(@PathVariable("film_id") long film_id) {
        return ResponseEntity.ok().body(filmService.getFilmEntity(film_id));
    }

    @GetMapping("/{film_id}/actors")
    @PreAuthorize("hasRole(\"ROLE_USER\")")
    ResponseEntity<Object> getAllActorsFromFilm(@PathVariable("film_id") long film_id) {
        return ResponseEntity.ok().body(storeActorService.getAllActorsFromFilm(film_id));
    }
}
