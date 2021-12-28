package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.dto.response.FilmGetRequest;
import nl.serkanertas.filmspringserver.exception.MediaNotFoundException;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.repository.FilmRepository;
import nl.serkanertas.filmspringserver.service.EntityToDtoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final EntityToDtoService entityToDtoService;

    public FilmService(FilmRepository filmRepository,
                       EntityToDtoService entityToDtoService) {
        this.filmRepository = filmRepository;
        this.entityToDtoService = entityToDtoService;
    }

    public boolean filmEntityExists(long film_id) {
        return filmRepository.existsById(film_id);
    }

    public FilmGetRequest getSearchedFilm(long film_id) {
        return entityToDtoService.mapFilmToDto(film_id);
    }

    public Film getFilmEntity(long film_id) {
        if (filmEntityExists(film_id)) {
            return filmRepository.findById(film_id).get();
        } else {
            throw new MediaNotFoundException("Film does not exist");
        }
    }

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

    public Iterable<Film> getAllFilmEntities() {
        return filmRepository.findAll();
    }

}
