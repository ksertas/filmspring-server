package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.FilmRepository;
import nl.serkanertas.filmspringserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FilmService {

    @Autowired
    private UserService userService;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private UserRepository userRepository;

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
}
