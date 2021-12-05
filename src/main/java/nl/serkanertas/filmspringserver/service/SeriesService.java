package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Actor;
import nl.serkanertas.filmspringserver.model.Series;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.ActorRepository;
import nl.serkanertas.filmspringserver.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private UserService userService;

    public Series getSeries(long series_id) { return seriesRepository.findById(series_id).get(); }

    public Iterable<Series> getAllSeries() { return seriesRepository.findAll(); }

    @Transactional
    public void storeSeriesToWatched(long user_id, long series_id) {
        User user = userService.getUser(user_id);
        Series series = seriesRepository.findById(series_id).get();
        series.getUsersWatchedSeries().add(user);
        seriesRepository.save(series);
    }

    @Transactional
    public void deleteSeriesFromUser(long user_id, long series_id) {
        User user = userService.getUser(user_id);
        Series series = seriesRepository.findById(series_id).get();
        series.getUsersWatchedSeries().remove(user);
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
