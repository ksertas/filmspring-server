package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.StoreActorService;
import nl.serkanertas.filmspringserver.service.models.SeriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> getAllSeries() { return ResponseEntity.ok().body(seriesService.getAllSeries()); }

    @GetMapping
    @PreAuthorize("hasRole(\"ROLE_USER\")")
    ResponseEntity<Object> getSearchedSeries(@RequestParam("search") String query) {
        return ResponseEntity.ok().body(seriesService.getSearchedSeries(query));
    }

    @GetMapping("/raw/{series_id}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> getSeriesEntity(@PathVariable("series_id") long series_id) {
        return ResponseEntity.ok().body(seriesService.getSeriesEntity(series_id));
    }

    @GetMapping("/{series_id}/actors")
    @PreAuthorize("hasRole(\"ROLE_USER\")")
    ResponseEntity<Object> getAllActorsFromSeries(@PathVariable("series_id") long series_id) {
        return ResponseEntity.ok().body(storeActorService.getAllActorsFromSeries(series_id));
    }
}
