package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.dto.response.FilmGetRequest;
import nl.serkanertas.filmspringserver.model.Actor;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.repository.ActorRepository;
import nl.serkanertas.filmspringserver.repository.FilmRepository;
import nl.serkanertas.filmspringserver.service.EntityToDtoService;
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

    private final EntityToDtoService entityToDtoService;

    public FilmService(UserService userService,
                       GroupService groupService,
                       FilmRepository filmRepository,
                       ActorRepository actorRepository,
                       EntityToDtoService entityToDtoService) {
        this.userService = userService;
        this.groupService = groupService;
        this.filmRepository = filmRepository;
        this.actorRepository = actorRepository;
        this.entityToDtoService = entityToDtoService;
    }

    public FilmGetRequest getSearchedFilm(long film_id) {
        return entityToDtoService.mapFilmToDto(film_id);
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

}
