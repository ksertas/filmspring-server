package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/{actor_id}")
    ResponseEntity<Object> getActor(@PathVariable("actor_id") long actor_id) {
        return ResponseEntity.ok().body(actorService.getActor(actor_id));
    }

    @GetMapping()
    ResponseEntity<Object> getAllActors() { return ResponseEntity.ok().body(actorService.getAllActors()); }

}
