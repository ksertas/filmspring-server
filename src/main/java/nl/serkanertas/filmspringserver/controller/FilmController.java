package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.dto.request.RatingDto;
import nl.serkanertas.filmspringserver.service.StoreActorService;
import nl.serkanertas.filmspringserver.service.models.FilmService;
import nl.serkanertas.filmspringserver.service.models.RatingService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService filmService;
    private final StoreActorService storeActorService;
    private final RatingService ratingService;

    public FilmController(FilmService filmService,
                          StoreActorService storeActorService,
                          @Lazy RatingService ratingService) {
        this.filmService = filmService;
        this.storeActorService = storeActorService;
        this.ratingService = ratingService;
    }

    @PostMapping("/{film_id}/rating/{user_id}")
    ResponseEntity<Object> rateFilm(@PathVariable("film_id") long film_id,
                                    @PathVariable("user_id") String user_id,
                                    @Valid @RequestBody RatingDto ratingDto) {
        ratingService.addFilmRating(user_id, film_id, ratingDto);
        return ResponseEntity.ok().body("Rating: " + ratingDto.getRating());
    }

    @GetMapping("/{film_id}/rating/{user_id}")
    ResponseEntity<Object> getFilmRating(@PathVariable("film_id") long film_id,
                                         @PathVariable("user_id") String user_id) {
        return ResponseEntity.ok().body(ratingService.getFilmRating(user_id, film_id));
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
