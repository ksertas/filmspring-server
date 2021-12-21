package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Actor;
import nl.serkanertas.filmspringserver.repository.ActorRepository;
import org.springframework.stereotype.Service;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor getActorEntity(long actor_id) {
        return actorRepository.findById(actor_id).get();
    }

    public Iterable<Actor> getAllActors() { return actorRepository.findAll(); }


}
