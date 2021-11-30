package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Actor;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.ActorRepository;
import nl.serkanertas.filmspringserver.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FilmService {

    @Autowired
    private UserService userService;

//    @Autowired
//    public void setActorService(ActorService actorService) {
//    }

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ActorRepository actorRepository;

    public Film getFilm(long film_id) { return filmRepository.findById(film_id).get(); }

    public Iterable<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Transactional
    public void storeFilmToWatched(long user_id, long film_id){
        User user = userService.getUser(user_id);
        Film film = filmRepository.findById(film_id).get();
        film.getUsersWatchedFilm().add(user);
        filmRepository.save(film);
    }

    @Transactional
    public void deleteFilmFromUser(long user_id, long film_id) {
        User user = userService.getUser(user_id);
        Film film = filmRepository.findById(film_id).get();
        film.getUsersWatchedFilm().remove(user);
        filmRepository.save(film);
    }

    public Iterable<Actor> getAllActorsFromFilm(long film_id) {
        return getFilm(film_id).getActorsInFilm();
    }

    @Transactional
    public void storeActorToFilm(long actor_id, long film_id) {
        Actor actor = actorRepository.findById(actor_id).get();
        Film film = getFilm(film_id);
        film.getActorsInFilm().add(actor);
        filmRepository.save(film);
    }

    @Transactional
    public void deleteActorFromFilm(long actor_id, long film_id) {
        Actor actor = actorRepository.findById(actor_id).get();
        Film film = getFilm(film_id);
        film.getActorsInFilm().remove(actor);
        filmRepository.save(film);
    }


}
