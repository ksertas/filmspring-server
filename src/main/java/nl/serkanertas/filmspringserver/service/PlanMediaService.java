package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.exception.BadRequestException;
import nl.serkanertas.filmspringserver.exception.ResourceAlreadyExistsException;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Group;
import nl.serkanertas.filmspringserver.model.Series;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.service.models.FilmService;
import nl.serkanertas.filmspringserver.service.models.GroupService;
import nl.serkanertas.filmspringserver.service.models.SeriesService;
import nl.serkanertas.filmspringserver.service.models.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PlanMediaService {

    private final UserService userService;
    private final GroupService groupService;
    private final FilmService filmService;
    private final SeriesService seriesService;

    public PlanMediaService(UserService userService,
                            GroupService groupService,
                            FilmService filmService,
                            SeriesService seriesService) {
        this.userService = userService;
        this.groupService = groupService;
        this.filmService = filmService;
        this.seriesService = seriesService;
    }

//    Users

    @Transactional
    public void storeFilmToPlanned(String user_id, long film_id){
        User user = userService.getUserEntity(user_id);
        Film film = filmService.getFilmEntity(film_id);
        if (film.getUsersPlannedFilm().contains(user)) {
            throw new ResourceAlreadyExistsException("Film already in planned");
        }
        if (film.getUsersWatchedFilm().contains(user)) {
            throw new ResourceAlreadyExistsException("Film already in watched");
        }
        film.getUsersPlannedFilm().add(user);
        filmService.saveFilmEntity(film);
    }

    @Transactional
    public void deleteFilmFromPlanned(String user_id, long film_id) {
        User user = userService.getUserEntity(user_id);
        Film film = filmService.getFilmEntity(film_id);
        if (!film.getUsersPlannedFilm().contains(user)) {
            throw new BadRequestException("Film not in planned");
        }
        film.getUsersPlannedFilm().remove(user);
        filmService.saveFilmEntity(film);
    }

    @Transactional
    public void storeSeriesToPlanned(String user_id, long series_id) {
        User user = userService.getUserEntity(user_id);
        Series series = seriesService.getSeriesEntity(series_id);
        if (series.getUsersPlannedSeries().contains(user)) {
            throw new ResourceAlreadyExistsException("Series already in planned");
        }
        if (series.getUsersWatchedSeries().contains(user)) {
            throw new ResourceAlreadyExistsException("Series already in watched");
        }
        series.getUsersPlannedSeries().add(user);
        seriesService.saveSeriesEntity(series);
    }

    @Transactional
    public void deleteSeriesFromPlanned(String user_id, long series_id) {
        User user = userService.getUserEntity(user_id);
        Series series = seriesService.getSeriesEntity(series_id);
        if (!series.getUsersPlannedSeries().contains(user)) {
            throw new BadRequestException("Series not in planned");
        }
        series.getUsersPlannedSeries().remove(user);
        seriesService.saveSeriesEntity(series);
    }

//    Groups

    @Transactional
    public void storeFilmToPlannedGroup(long group_id, long film_id){
        Group group = groupService.getGroupEntity(group_id);
        Film film = filmService.getFilmEntity(film_id);
        if (group.getPlannedFlms().contains(film)) {
            throw new ResourceAlreadyExistsException("Film already in planned");
        }
        film.getGroupPlannedFilm().add(group);
        filmService.saveFilmEntity(film);
    }

    @Transactional
    public void deleteFilmFromPlannedGroup(long group_id, long film_id) {
        Group group = groupService.getGroupEntity(group_id);
        Film film = filmService.getFilmEntity(film_id);
        if (!group.getPlannedFlms().contains(film)) {
            throw new BadRequestException("Film not in planned");
        }
        film.getGroupPlannedFilm().remove(group);
        filmService.saveFilmEntity(film);
    }

    @Transactional
    public void storeSeriesToPlannedGroup(long group_id, long series_id) {
        Group group = groupService.getGroupEntity(group_id);
        Series series = seriesService.getSeriesEntity(series_id);
        if (group.getPlannedSeries().contains(series)) {
            throw new ResourceAlreadyExistsException("Series already in planned");
        }
        series.getGroupPlannedSeries().add(group);
        seriesService.saveSeriesEntity(series);
    }

    @Transactional
    public void deleteSeriesFromPlannedGroup(long group_id, long series_id) {
        Group group = groupService.getGroupEntity(group_id);
        Series series = seriesService.getSeriesEntity(series_id);
        if (!group.getPlannedSeries().contains(series)) {
            throw new BadRequestException("Series not in planned");
        }
        series.getGroupPlannedSeries().remove(group);
        seriesService.saveSeriesEntity(series);
    }
}
