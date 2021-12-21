package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.PlanMediaService;
import nl.serkanertas.filmspringserver.service.WatchMediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class MediaUsersController {

    private final WatchMediaService watchMediaService;
    private final PlanMediaService planMediaService;

    public MediaUsersController(WatchMediaService watchMediaService,
                                 PlanMediaService planMediaService) {
        this.watchMediaService = watchMediaService;
        this.planMediaService = planMediaService;
    }

    // films

    // {user_id} will be replaced with current user from security context
    @PutMapping("/films/watched/{film_id}/users/{user_id}")
    ResponseEntity<Object> addFilmToWatched(@PathVariable("user_id") String user_id,
                                            @PathVariable("film_id") long film_id) {
        watchMediaService.storeFilmToWatched(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/films/watched/{film_id}/users/{user_id}")
    ResponseEntity<Object> deleteFilmFromWatched(@PathVariable("user_id") String user_id,
                                                 @PathVariable("film_id") long film_id) {
        watchMediaService.deleteFilmFromWatched(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/films/planned/{film_id}/users/{user_id}")
    ResponseEntity<Object> addFilmToPlanned(@PathVariable("user_id") String user_id,
                                            @PathVariable("film_id") long film_id) {
        planMediaService.storeFilmToPlanned(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/films/planned/{film_id}/users/{user_id}")
    ResponseEntity<Object> deleteFilmFromPlanned(@PathVariable("user_id") String user_id,
                                                 @PathVariable("film_id") long film_id) {
        planMediaService.deleteFilmFromPlanned(user_id, film_id);
        return ResponseEntity.noContent().build();
    }

    // series

    @PutMapping("/series/watched/{series_id}/users/{user_id}")
    ResponseEntity<Object> addSeriesToWatched(@PathVariable("user_id") String user_id,
                                              @PathVariable("series_id") long series_id) {
        watchMediaService.storeSeriesToWatched(user_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/series/watched/{series_id}/users/{user_id}")
    ResponseEntity<Object> deleteSeriesFromWatched(@PathVariable("user_id") String user_id,
                                                   @PathVariable("series_id") long series_id) {
        watchMediaService.deleteSeriesFromWatched(user_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/series/planned/{series_id}/users/{user_id}")
    ResponseEntity<Object> addSeriesToPlanned(@PathVariable("user_id") String user_id,
                                              @PathVariable("series_id") long series_id) {
        planMediaService.storeSeriesToPlanned(user_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/series/planned/{series_id}/users/{user_id}")
    ResponseEntity<Object> deleteSeriesFromPlanned(@PathVariable("user_id") String user_id,
                                                   @PathVariable("series_id") long series_id) {
        planMediaService.deleteSeriesFromPlanned(user_id, series_id);
        return ResponseEntity.noContent().build();
    }



}
