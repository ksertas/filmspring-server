package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Actor;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.repository.ActorRepository;
import nl.serkanertas.filmspringserver.repository.FilmRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {
    private final UserService userService;
    private final GroupService groupService;
    private final FilmRepository filmRepository;
    private final ActorRepository actorRepository;

    public FilmService(UserService userService,
                       GroupService groupService,
                       FilmRepository filmRepository,
                       ActorRepository actorRepository) {
        this.userService = userService;
        this.groupService = groupService;
        this.filmRepository = filmRepository;
        this.actorRepository = actorRepository;
    }


    public Film getFilmEntity(long film_id) { return filmRepository.findById(film_id).get(); }

    public void saveFilmEntity(Film film) {
        filmRepository.save(film);
    }

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

    public Iterable<Actor> getAllActorsFromFilm(long film_id) {
        return getFilmEntity(film_id).getActorsInFilm();
    }

}
