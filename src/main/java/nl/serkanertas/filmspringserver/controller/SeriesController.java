package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.dto.request.RatingDto;
import nl.serkanertas.filmspringserver.service.StoreActorService;
import nl.serkanertas.filmspringserver.service.models.SeriesRatingService;
import nl.serkanertas.filmspringserver.service.models.SeriesService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/series")
public class SeriesController {

    private final SeriesService seriesService;
    private final StoreActorService storeActorService;
    private final SeriesRatingService seriesRatingService;

    public SeriesController(SeriesService seriesService,
                            StoreActorService storeActorService,
                            @Lazy SeriesRatingService seriesRatingService) {
        this.seriesService = seriesService;
        this.storeActorService = storeActorService;
        this.seriesRatingService = seriesRatingService;
    }

    @GetMapping("/{series_id}")
    @PreAuthorize("hasRole(\"ROLE_USER\")")
    ResponseEntity<Object> getSeries(@PathVariable("series_id") long series_id) {
        return ResponseEntity.ok().body(seriesService.getSearchedSeries(series_id));
    }

    @GetMapping
    @PreAuthorize("hasRole(\"ROLE_USER\")")
    ResponseEntity<Object> getSearchedSeries(@RequestParam("search") String query) {
        return ResponseEntity.ok().body(seriesService.getSearchedSeries(query));
    }

    @GetMapping("/{series_id}/rating/{user_id}")
    @PreAuthorize("@postAuthService.isCurrentUser(#user_id)")
    ResponseEntity<Object> getSeriesRating(@PathVariable("series_id") long series_id,
                                         @PathVariable("user_id") String user_id) {
        return ResponseEntity.ok().body(seriesRatingService.getSeriesRating(user_id, series_id));
    }

    @PutMapping("/{series_id}/rating/{user_id}")
    @PreAuthorize("@postAuthService.userHasWatchedSeries(#user_id, #series_id) and " +
            "@postAuthService.isCurrentUser(#user_id)")
    ResponseEntity<Object> rateWatchedSeries(@PathVariable("series_id") long series_id,
                                           @PathVariable("user_id") String user_id,
                                           @Valid @RequestBody RatingDto ratingDto) {
        seriesRatingService.updateSeriesRating(user_id, series_id, ratingDto);
        return ResponseEntity.ok().body("Rating: " + ratingDto.getRating());
    }

    @GetMapping("/{series_id}/actors")
    @PreAuthorize("hasRole(\"ROLE_USER\")")
    ResponseEntity<Object> getAllActorsFromSeries(@PathVariable("series_id") long series_id) {
        return ResponseEntity.ok().body(storeActorService.getAllActorsFromSeries(series_id));
    }

    @GetMapping("/raw")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> getAllSeriesEntities() { return ResponseEntity.ok().body(seriesService.getAllSeriesEntities()); }


    @GetMapping("/raw/{series_id}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> getSeriesEntity(@PathVariable("series_id") long series_id) {
        return ResponseEntity.ok().body(seriesService.getSeriesEntity(series_id));
    }

}
