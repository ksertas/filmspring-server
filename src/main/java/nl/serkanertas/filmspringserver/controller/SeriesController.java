package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.StoreActorService;
import nl.serkanertas.filmspringserver.service.models.SeriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/series")
public class SeriesController {

    private final SeriesService seriesService;
    private final StoreActorService storeActorService;

    public SeriesController(SeriesService seriesService, StoreActorService storeActorService) {
        this.seriesService = seriesService;
        this.storeActorService = storeActorService;
    }

    @GetMapping("/raw")
    ResponseEntity<Object> getAllSeries() { return ResponseEntity.ok().body(seriesService.getAllSeries()); }

    @GetMapping
    ResponseEntity<Object> getSearchedSeries(@RequestParam("search") String query) {
        return ResponseEntity.ok().body(seriesService.getSearchedSeries(query));
    }

    @GetMapping("/{series_id}")
    ResponseEntity<Object> getSeries(@PathVariable("series_id") long series_id) {
        return ResponseEntity.ok().body(seriesService.getSeriesEntity(series_id));
    }

    @GetMapping("/{series_id}/actors")
    ResponseEntity<Object> getAllActorsFromSeries(@PathVariable("series_id") long series_id) {
        return ResponseEntity.ok().body(storeActorService.getAllActorsFromSeries(series_id));
    }
}
