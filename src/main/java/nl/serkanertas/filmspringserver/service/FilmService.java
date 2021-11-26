package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.FilmRepository;
import nl.serkanertas.filmspringserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private UserRepository userRepository;

    public Iterable<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public void addFilmToWatched(long user_id, long film_id){
        User user = userRepository.getById(user_id);
        Film film = filmRepository.getById(film_id);
        film.getUsersWatchedFilm().add(user);
        filmRepository.save(film);
    }
}
