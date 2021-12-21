package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.SeriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/series")
public class SeriesController {

    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping("/raw")
    ResponseEntity<Object> getAllSeries() { return ResponseEntity.ok().body(seriesService.getAllSeries()); }

    @GetMapping
    ResponseEntity<Object> getSearchedSeries(@RequestParam("search") String query) {
        return ResponseEntity.ok().body(seriesService.getSearchedSeries(query));
    }

    @PutMapping("/watched/{series_id}/users/{user_id}")
    ResponseEntity<Object> addSeriesToWatched(@PathVariable("user_id") String user_id,
                                            @PathVariable("series_id") long series_id) {
        seriesService.storeSeriesToWatched(user_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/watched/{series_id}/users/{user_id}")
    ResponseEntity<Object> deleteSeriesFromWatched(@PathVariable("user_id") String user_id,
                                                 @PathVariable("series_id") long series_id) {
        seriesService.deleteSeriesFromWatched(user_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/planned/{series_id}/users/{user_id}")
    ResponseEntity<Object> addSeriesToPlanned(@PathVariable("user_id") String user_id,
                                              @PathVariable("series_id") long series_id) {
        seriesService.storeSeriesToPlanned(user_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/planned/{series_id}/users/{user_id}")
    ResponseEntity<Object> deleteSeriesFromPlanned(@PathVariable("user_id") String user_id,
                                                   @PathVariable("series_id") long series_id) {
        seriesService.deleteSeriesFromPlanned(user_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/planned/{series_id}/groups/{group_id}")
    ResponseEntity<Object> addSeriesToPlannedGroup(@PathVariable("group_id") long group_id,
                                              @PathVariable("series_id") long series_id) {
        seriesService.storeSeriesToPlannedGroup(group_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/planned/{series_id}/groups/{group_id}")
    ResponseEntity<Object> deleteSeriesFromPlannedGroup(@PathVariable("group_id") long group_id,
                                                   @PathVariable("series_id") long series_id) {
        seriesService.deleteSeriesFromPlannedGroup(group_id, series_id);
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
