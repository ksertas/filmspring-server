package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Actor;
import nl.serkanertas.filmspringserver.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public Actor getActor(long actor_id) {
        return actorRepository.findById(actor_id).get();
    }

    public Iterable<Actor> getAllActors() { return actorRepository.findAll(); }


}
