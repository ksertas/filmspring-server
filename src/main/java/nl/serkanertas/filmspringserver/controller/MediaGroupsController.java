package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.PlanMediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class MediaGroupsController {

    private final PlanMediaService planMediaService;

    public MediaGroupsController(PlanMediaService planMediaService) {
        this.planMediaService = planMediaService;
    }

    @PutMapping("/{group_id}/films/planned/{film_id}")
    ResponseEntity<Object> addFilmToPlannedGroup(@PathVariable("group_id") long group_id,
                                                 @PathVariable("film_id") long film_id) {
        planMediaService.storeFilmToPlannedGroup(group_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{group_id}/films/planned/{film_id}")
    ResponseEntity<Object> deleteFilmFromPlannedGroup(@PathVariable("group_id") long group_id,
                                                      @PathVariable("film_id") long film_id) {
        planMediaService.deleteFilmFromPlannedGroup(group_id, film_id);
        return ResponseEntity.noContent().build();
    }

    // series

    @PutMapping("/{group_id}/series/planned/{series_id}")
    ResponseEntity<Object> addSeriesToPlannedGroup(@PathVariable("group_id") long group_id,
                                                   @PathVariable("series_id") long series_id) {
        planMediaService.storeSeriesToPlannedGroup(group_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{group_id}/series/planned/{series_id}")
    ResponseEntity<Object> deleteSeriesFromPlannedGroup(@PathVariable("group_id") long group_id,
                                                        @PathVariable("series_id") long series_id) {
        planMediaService.deleteSeriesFromPlannedGroup(group_id, series_id);
        return ResponseEntity.noContent().build();
    }
}
