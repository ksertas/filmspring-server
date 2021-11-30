package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Actor;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.repository.ActorRepository;
import nl.serkanertas.filmspringserver.repository.FilmRepository;
import nl.serkanertas.filmspringserver.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    public Actor getActor(long actor_id) {
        return actorRepository.findById(actor_id).get();
    }

    public Iterable<Actor> getAllActors() { return actorRepository.findAll(); }


}
