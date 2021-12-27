package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.exception.ActorNotFoundException;
import nl.serkanertas.filmspringserver.model.Actor;
import nl.serkanertas.filmspringserver.repository.ActorRepository;
import org.springframework.stereotype.Service;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public boolean actorEntityExists(long actor_id) {
        return actorRepository.existsById(actor_id);
    }

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor getActorEntity(long actor_id) {
        if (actorEntityExists(actor_id)) return actorRepository.findById(actor_id).get();
        else throw new ActorNotFoundException("Actor does not exist");
    }

    public Iterable<Actor> getAllActorEntities() { return actorRepository.findAll(); }
}
