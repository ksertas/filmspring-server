package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.*;
import nl.serkanertas.filmspringserver.repository.ActorRepository;
import nl.serkanertas.filmspringserver.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    public Series getSeries(long series_id) { return seriesRepository.findById(series_id).get(); }

    public Iterable<Series> getAllSeries() { return seriesRepository.findAll(); }

    @Transactional
    public List<Series> getSearchedFilms(String query) {
        Iterable<Series> foundSeries = seriesRepository.findSeriesByTitleContainsIgnoreCase(query);
        ArrayList<Series> toReturnSeries = new ArrayList<>();
        for (Series series : foundSeries) {
            toReturnSeries.add(series);
        }
        return toReturnSeries;
    }

    @Transactional
    public void storeSeriesToWatched(String user_id, long series_id) {
        User user = userService.getUserEntity(user_id);
        Series series = seriesRepository.findById(series_id).get();
        series.getUsersWatchedSeries().add(user);
        seriesRepository.save(series);
    }

    @Transactional
    public void deleteSeriesFromWatched(String user_id, long series_id) {
        User user = userService.getUserEntity(user_id);
        Series series = seriesRepository.findById(series_id).get();
        series.getUsersWatchedSeries().remove(user);
        seriesRepository.save(series);
    }

    @Transactional
    public void storeSeriesToPlanned(String user_id, long series_id) {
        User user = userService.getUserEntity(user_id);
        Series series = seriesRepository.findById(series_id).get();
        series.getUsersPlannedSeries().add(user);
        seriesRepository.save(series);
    }

    @Transactional
    public void deleteSeriesFromPlanned(String user_id, long series_id) {
        User user = userService.getUserEntity(user_id);
        Series series = seriesRepository.findById(series_id).get();
        series.getUsersPlannedSeries().remove(user);
        seriesRepository.save(series);
    }

    @Transactional
    public void storeSeriesToPlannedGroup(long group_id, long series_id) {
        Group group = groupService.getGroupEntity(group_id);
        Series series = seriesRepository.findById(series_id).get();
        series.getGroupPlannedSeries().add(group);
        seriesRepository.save(series);
    }

    @Transactional
    public void deleteSeriesFromPlannedGroup(long group_id, long series_id) {
        Group group = groupService.getGroupEntity(group_id);
        Series series = seriesRepository.findById(series_id).get();
        series.getGroupPlannedSeries().remove(group);
        seriesRepository.save(series);
    }

    public Iterable<Actor> getAllActorsFromSeries(long series_id) {
        return getSeries(series_id).getActorsInSeries();
    }

    @Transactional
    public void storeActorToSeries(long actor_id, long series_id) {
        Actor actor = actorRepository.findById(actor_id).get();
        Series series = getSeries(series_id);
        series.getActorsInSeries().add(actor);
        seriesRepository.save(series);
    }

    @Transactional
    public void deleteActorFromSeries(long actor_id, long series_id) {
        Actor actor = actorRepository.findById(actor_id).get();
        Series series = getSeries(series_id);
        series.getActorsInSeries().remove(actor);
        seriesRepository.save(series);
    }
}
