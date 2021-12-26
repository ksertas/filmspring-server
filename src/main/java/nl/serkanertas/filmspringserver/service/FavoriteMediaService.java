package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Series;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.service.models.FilmService;
import nl.serkanertas.filmspringserver.service.models.SeriesService;
import nl.serkanertas.filmspringserver.service.models.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FavoriteMediaService {
    private final UserService userService;
    private final FilmService filmService;
    private final SeriesService seriesService;

    public FavoriteMediaService(UserService userService,
                                FilmService filmService,
                                SeriesService seriesService) {
        this.userService = userService;
        this.filmService = filmService;
        this.seriesService = seriesService;
    }

    @Transactional
    public void storeFilmToFavorites(String user_id, long film_id) {
        User user = userService.getUserEntity(user_id);
        Film film = filmService.getFilmEntity(film_id);
        film.getUsersFavoriteFilm().add(user);
        filmService.saveFilmEntity(film);
    }

    @Transactional
    public void deleteFilmFromFavorites(String user_id, long film_id) {
        User user = userService.getUserEntity(user_id);
        Film film = filmService.getFilmEntity(film_id);
        film.getUsersFavoriteFilm().remove(user);
        filmService.saveFilmEntity(film);
    }

    @Transactional
    public void storeSeriesToFavorites(String user_id, long series_id) {
        User user = userService.getUserEntity(user_id);
        Series series = seriesService.getSeriesEntity(series_id);
        series.getUsersFavoriteSeries().add(user);
        seriesService.saveSeriesEntity(series);
    }

    @Transactional
    public void deleteSeriesFromFavorites(String user_id, long series_id) {
        User user = userService.getUserEntity(user_id);
        Series series = seriesService.getSeriesEntity(series_id);
        series.getUsersFavoriteSeries().remove(user);
        seriesService.saveSeriesEntity(series);
    }

}
