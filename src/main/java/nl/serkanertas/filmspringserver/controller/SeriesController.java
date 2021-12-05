package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @GetMapping()
    ResponseEntity<Object> getAllSeries() { return ResponseEntity.ok().body(seriesService.getAllSeries()); }

    @PutMapping("/{series_id}/users/{user_id}")
    ResponseEntity<Object> addFilmToWatched(@PathVariable("user_id") long user_id,
                                            @PathVariable("series_id") long series_id) {
        seriesService.storeSeriesToWatched(user_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{series_id}/users/{user_id}")
    ResponseEntity<Object> deleteFilmFromWatched(@PathVariable("user_id") long user_id,
                                                 @PathVariable("series_id") long series_id) {
        seriesService.deleteSeriesFromUser(user_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{series_id}/actors")
    ResponseEntity<Object> getAllActorsFromSeries(@PathVariable("series_id") long series_id) {
        return ResponseEntity.ok().body(seriesService.getAllActorsFromSeries(series_id));
    }

    @PutMapping("/{series_id}/actors/{actor_id}")
    ResponseEntity<Object> storeActorToSeries(@PathVariable("series_id") long series_id,
                                              @PathVariable("actor_id") long actor_id) {
        seriesService.storeActorToSeries(actor_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{series_id}/actors/{actor_id}")
    ResponseEntity<Object> deleteActorFromSeries(@PathVariable("series_id") long series_id,
                                                 @PathVariable("actor_id") long actor_id) {
        seriesService.deleteActorFromSeries(actor_id, series_id);
        return ResponseEntity.noContent().build();
    }

}
