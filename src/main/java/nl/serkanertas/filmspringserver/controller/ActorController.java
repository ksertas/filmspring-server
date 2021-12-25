package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.StoreActorService;
import nl.serkanertas.filmspringserver.service.models.ActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;
    private final StoreActorService storeActorService;

    public ActorController(ActorService actorService,
                           StoreActorService storeActorService) {
        this.actorService = actorService;
        this.storeActorService = storeActorService;
    }

    @GetMapping()
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> getAllActors() { return ResponseEntity.ok().body(actorService.getAllActorEntities()); }

    @GetMapping("/{actor_id}")
    @PreAuthorize("hasRole(\"ROLE_USER\")")
    ResponseEntity<Object> getActor(@PathVariable("actor_id") long actor_id) {
        return ResponseEntity.ok().body(actorService.getActorEntity(actor_id));
    }

    // films and series

    @PutMapping("/films/{film_id}/actors/{actor_id}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> storeActorToFilm(@PathVariable("actor_id") long actor_id, @PathVariable("film_id") long film_id) {
        storeActorService.storeActorToFilm(actor_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/films/{film_id}/actors/{actor_id}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> deleteActorFromFilm(@PathVariable("actor_id") long actor_id,
                                               @PathVariable("film_id") long film_id) {
        storeActorService.deleteActorFromFilm(actor_id, film_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/series/{series_id}/actors/{actor_id}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> storeActorToSeries(@PathVariable("series_id") long series_id,
                                              @PathVariable("actor_id") long actor_id) {
        storeActorService.storeActorToSeries(actor_id, series_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/series/{series_id}/actors/{actor_id}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> deleteActorFromSeries(@PathVariable("series_id") long series_id,
                                                 @PathVariable("actor_id") long actor_id) {
        storeActorService.deleteActorFromSeries(actor_id, series_id);
        return ResponseEntity.noContent().build();
    }
}
