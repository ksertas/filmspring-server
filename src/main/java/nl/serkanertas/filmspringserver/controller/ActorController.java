package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.ActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/{actor_id}")
    ResponseEntity<Object> getActor(@PathVariable("actor_id") long actor_id) {
        return ResponseEntity.ok().body(actorService.getActor(actor_id));
    }

    @GetMapping()
    ResponseEntity<Object> getAllActors() { return ResponseEntity.ok().body(actorService.getAllActors()); }

}
