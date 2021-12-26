package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.FavoriteMediaService;
import nl.serkanertas.filmspringserver.service.PlanMediaService;
import nl.serkanertas.filmspringserver.service.WatchMediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class MediaUsersController {

    private final WatchMediaService watchMediaService;
    private final PlanMediaService planMediaService;
    private final FavoriteMediaService favoriteMediaService;

    public MediaUsersController(WatchMediaService watchMediaService,
                                 PlanMediaService planMediaService,
                                FavoriteMediaService favoriteMediaService) {
        this.watchMediaService = watchMediaService;
        this.planMediaService = planMediaService;
        this.favoriteMediaService = favoriteMediaService;
    }

    // films

    @PutMapping("/{user_id}/films/watched/{film_id}")
    @PreAuthorize("@postAuthService.isCurrentUser(#user_id)")
    ResponseEntity<Object> addFilmToWatched(@PathVariable("user_id") String user_id,
                                            @PathVariable("film_id") long film_id) {
        watchMediaService.storeFilmToWatched(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{user_id}/films/watched/{film_id}")
    @PreAuthorize("@postAuthService.isCurrentUser(#user_id)")
    ResponseEntity<Object> deleteFilmFromWatched(@PathVariable("user_id") String user_id,
                                                 @PathVariable("film_id") long film_id) {
        watchMediaService.deleteFilmFromWatched(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{user_id}/films/planned/{film_id}")
    @PreAuthorize("@postAuthService.isCurrentUser(#user_id)")
    ResponseEntity<Object> addFilmToPlanned(@PathVariable("user_id") String user_id,
                                            @PathVariable("film_id") long film_id) {
        planMediaService.storeFilmToPlanned(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{user_id}/films/planned/{film_id}")
    @PreAuthorize("@postAuthService.isCurrentUser(#user_id)")
    ResponseEntity<Object> deleteFilmFromPlanned(@PathVariable("user_id") String user_id,
                                                 @PathVariable("film_id") long film_id) {
        planMediaService.deleteFilmFromPlanned(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{user_id}/films/favorites/{film_id}")
    ResponseEntity<Object> addFilmToFavorites(@PathVariable("user_id") String user_id,
                                              @PathVariable("film_id") long film_id) {
        favoriteMediaService.storeFilmToFavorites(user_id, film_id);
        return ResponseEntity.ok().body("Film added to favorites");
    }

    @DeleteMapping("/{user_id}/films/favorites/{film_id}")
    ResponseEntity<Object> removeFilmToFavorites(@PathVariable("user_id") String user_id,
                                              @PathVariable("film_id") long film_id) {
        favoriteMediaService.deleteFilmFromFavorites(user_id, film_id);
        return ResponseEntity.ok().body("Film removed from favorites");
    }

    // series

    @PutMapping("/{user_id}/series/watched/{series_id}")
    @PreAuthorize("@postAuthService.isCurrentUser(#user_id)")
    ResponseEntity<Object> addSeriesToWatched(@PathVariable("user_id") String user_id,
                                              @PathVariable("series_id") long series_id) {
        watchMediaService.storeSeriesToWatched(user_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{user_id}/series/watched/{series_id}")
    @PreAuthorize("@postAuthService.isCurrentUser(#user_id)")
    ResponseEntity<Object> deleteSeriesFromWatched(@PathVariable("user_id") String user_id,
                                                   @PathVariable("series_id") long series_id) {
        watchMediaService.deleteSeriesFromWatched(user_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{user_id}/series/planned/{series_id}")
    @PreAuthorize("@postAuthService.isCurrentUser(#user_id)")
    ResponseEntity<Object> addSeriesToPlanned(@PathVariable("user_id") String user_id,
                                              @PathVariable("series_id") long series_id) {
        planMediaService.storeSeriesToPlanned(user_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{user_id}/series/planned/{series_id}")
    @PreAuthorize("@postAuthService.isCurrentUser(#user_id)")
    ResponseEntity<Object> deleteSeriesFromPlanned(@PathVariable("user_id") String user_id,
                                                   @PathVariable("series_id") long series_id) {
        planMediaService.deleteSeriesFromPlanned(user_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{user_id}/series/favorites/{series_id}")
    ResponseEntity<Object> addSeriesToFavorites(@PathVariable("user_id") String user_id,
                                              @PathVariable("series_id") long series_id) {
        favoriteMediaService.storeSeriesToFavorites(user_id, series_id);
        return ResponseEntity.ok().body("Series added to favorites");
    }

    @DeleteMapping("/{user_id}/series/favorites/{series_id}")
    ResponseEntity<Object> removeSeriesToFavorites(@PathVariable("user_id") String user_id,
                                                 @PathVariable("series_id") long series_id) {
        favoriteMediaService.deleteSeriesFromFavorites(user_id, series_id);
        return ResponseEntity.ok().body("Series removed from favorites");
    }

}
