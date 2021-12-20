package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.dto.response.SearchedUserGetRequest;
import nl.serkanertas.filmspringserver.model.Actor;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Group;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.ActorRepository;
import nl.serkanertas.filmspringserver.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ActorRepository actorRepository;

    public Film getFilm(long film_id) { return filmRepository.findById(film_id).get(); }

    @Transactional
    public List<Film> getSearchedFilms(String query) {
        Iterable<Film> films = filmRepository.findFIlmByTitleContainsIgnoreCase(query);
        ArrayList<Film> toReturnFilms = new ArrayList<>();
        for (Film film : films) {
            toReturnFilms.add(film);
        }
        return toReturnFilms;
    }

    public Iterable<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Transactional
    public void storeFilmToWatched(String user_id, long film_id){
        User user = userService.getUserEntity(user_id);
        Film film = filmRepository.findById(film_id).get();
        film.getUsersWatchedFilm().add(user);
        filmRepository.save(film);
    }

    @Transactional
    public void deleteFilmFromWatched(String user_id, long film_id) {
        User user = userService.getUserEntity(user_id);
        Film film = filmRepository.findById(film_id).get();
        film.getUsersWatchedFilm().remove(user);
        filmRepository.save(film);
    }

    @Transactional
    public void storeFilmToPlanned(String user_id, long film_id){
        User user = userService.getUserEntity(user_id);
        Film film = filmRepository.findById(film_id).get();
        film.getUsersPlannedFilm().add(user);
        filmRepository.save(film);
    }

    @Transactional
    public void deleteFilmFromPlanned(String user_id, long film_id) {
        User user = userService.getUserEntity(user_id);
        Film film = filmRepository.findById(film_id).get();
        film.getUsersPlannedFilm().remove(user);
        filmRepository.save(film);
    }

    @Transactional
    public void storeFilmToPlannedGroup(long group_id, long film_id){
        Group group = groupService.getGroupEntity(group_id);
        Film film = filmRepository.findById(film_id).get();
        film.getGroupPlannedFilm().add(group);
        filmRepository.save(film);
    }

    @Transactional
    public void deleteFilmFromPlannedGroup(long group_id, long film_id) {
        Group group = groupService.getGroupEntity(group_id);
        Film film = filmRepository.findById(film_id).get();
        film.getGroupPlannedFilm().remove(group);
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
