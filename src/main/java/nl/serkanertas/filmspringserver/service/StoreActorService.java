package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Actor;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Series;
import nl.serkanertas.filmspringserver.service.models.ActorService;
import nl.serkanertas.filmspringserver.service.models.FilmService;
import nl.serkanertas.filmspringserver.service.models.SeriesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StoreActorService {

    private final ActorService actorService;
    private final FilmService filmService;
    private final SeriesService seriesService;

    public StoreActorService(ActorService actorService,
                             FilmService filmService,
                             SeriesService seriesService) {
        this.actorService = actorService;
        this.filmService = filmService;
        this.seriesService = seriesService;
    }

    @Transactional
    public Iterable<Actor> getAllActorsFromFilm(long film_id) {
        return filmService.getFilmEntity(film_id).getActorsInFilm();
    }

    @Transactional
    public Iterable<Actor> getAllActorsFromSeries(long series_id) {
        return seriesService.getSeriesEntity(series_id).getActorsInSeries();
    }

    @Transactional
    public void storeActorToFilm(long actor_id, long film_id) {
        Actor actor = actorService.getActorEntity(actor_id);
        Film film = filmService.getFilmEntity(film_id);
        film.getActorsInFilm().add(actor);
        filmService.saveFilmEntity(film);
    }

    @Transactional
    public void deleteActorFromFilm(long actor_id, long film_id) {
        Actor actor = actorService.getActorEntity(actor_id);
        Film film = filmService.getFilmEntity(film_id);
        film.getActorsInFilm().remove(actor);
        filmService.saveFilmEntity(film);
    }

    @Transactional
    public void storeActorToSeries(long actor_id, long series_id) {
        Actor actor = actorService.getActorEntity(actor_id);
        Series series = seriesService.getSeriesEntity(series_id);
        series.getActorsInSeries().add(actor);
        seriesService.saveSeriesEntity(series);
    }

    @Transactional
    public void deleteActorFromSeries(long actor_id, long series_id) {
        Actor actor = actorService.getActorEntity(actor_id);
        Series series = seriesService.getSeriesEntity(series_id);
        series.getActorsInSeries().remove(actor);
        seriesService.saveSeriesEntity(series);
    }

}
