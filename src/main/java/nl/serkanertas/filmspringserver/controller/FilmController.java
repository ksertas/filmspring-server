package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.dto.request.RatingDto;
import nl.serkanertas.filmspringserver.service.StoreActorService;
import nl.serkanertas.filmspringserver.service.models.FilmService;
import nl.serkanertas.filmspringserver.service.models.FilmRatingService;
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
    private final FilmRatingService filmRatingService;

    public FilmController(FilmService filmService,
                          StoreActorService storeActorService,
                          @Lazy FilmRatingService filmRatingService) {
        this.filmService = filmService;
        this.storeActorService = storeActorService;
        this.filmRatingService = filmRatingService;
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

    @GetMapping("/{film_id}/rating/{user_id}")
    @PreAuthorize("@postAuthService.isCurrentUser(#user_id)")
    ResponseEntity<Object> getFilmRating(@PathVariable("film_id") long film_id,
                                         @PathVariable("user_id") String user_id) {
        return ResponseEntity.ok().body(filmRatingService.getFilmRating(user_id, film_id));
    }

    @PutMapping("/{film_id}/rating/{user_id}")
    @PreAuthorize("@postAuthService.userHasWatchedFilm(#user_id, #film_id) and " +
            "@postAuthService.isCurrentUser(#user_id)")
    ResponseEntity<Object> rateWatchedFilm(@PathVariable("film_id") long film_id,
                                           @PathVariable("user_id") String user_id,
                                           @Valid @RequestBody RatingDto ratingDto) {
        filmRatingService.updateFilmRating(user_id, film_id, ratingDto);
        return ResponseEntity.ok().body("Rating: " + ratingDto.getRating());
    }

    @GetMapping("/raw")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> getAllFilmEntities() {
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
