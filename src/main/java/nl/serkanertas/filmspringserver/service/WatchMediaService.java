package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.dto.request.RatingDto;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Series;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.service.models.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WatchMediaService {

    private final UserService userService;
    private final FilmService filmService;
    private final SeriesService seriesService;
    private final FilmRatingService filmRatingService;
    private final SeriesRatingService seriesRatingService;

    public WatchMediaService(UserService userService,
                             FilmService filmService,
                             SeriesService seriesService,
                             @Lazy FilmRatingService filmRatingService,
                             @Lazy SeriesRatingService seriesRatingService) {
        this.userService = userService;
        this.filmService = filmService;
        this.seriesService = seriesService;
        this.filmRatingService = filmRatingService;
        this.seriesRatingService = seriesRatingService;
    }

//    Users

    @Transactional
    public void storeFilmToWatched(String user_id, long film_id){
        User user = userService.getUserEntity(user_id);
        Film film = filmService.getFilmEntity(film_id);
        film.getUsersWatchedFilm().add(user);
        filmService.saveFilmEntity(film);
        RatingDto ratingDto = new RatingDto();
        ratingDto.setRating(0);
        filmRatingService.addFilmRating(user_id, film_id, ratingDto);
    }

    @Transactional
    public void deleteFilmFromWatched(String user_id, long film_id) {
        User user = userService.getUserEntity(user_id);
        Film film = filmService.getFilmEntity(film_id);
        film.getUsersWatchedFilm().remove(user);
        filmService.saveFilmEntity(film);
        filmRatingService.deleteFilmRating(user_id, film_id);
    }

    @Transactional
    public void storeSeriesToWatched(String user_id, long series_id) {
        User user = userService.getUserEntity(user_id);
        Series series = seriesService.getSeriesEntity(series_id);
        series.getUsersWatchedSeries().add(user);
        seriesService.saveSeriesEntity(series);
        RatingDto ratingDto = new RatingDto();
        ratingDto.setRating(0);
        seriesRatingService.addSeriesRating(user_id, series_id, ratingDto);
    }

    @Transactional
    public void deleteSeriesFromWatched(String user_id, long series_id) {
        User user = userService.getUserEntity(user_id);
        Series series = seriesService.getSeriesEntity(series_id);
        series.getUsersWatchedSeries().remove(user);
        seriesService.saveSeriesEntity(series);
        seriesRatingService.deleteSeriesRating(user_id, series_id);
    }
}
