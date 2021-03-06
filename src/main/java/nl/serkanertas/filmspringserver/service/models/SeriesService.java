package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.dto.response.SeriesGetRequest;
import nl.serkanertas.filmspringserver.exception.MediaNotFoundException;
import nl.serkanertas.filmspringserver.model.*;
import nl.serkanertas.filmspringserver.repository.ActorRepository;
import nl.serkanertas.filmspringserver.repository.SeriesRepository;
import nl.serkanertas.filmspringserver.service.EntityToDtoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;

    private final ActorRepository actorRepository;

    private final UserService userService;

    private final GroupService groupService;

    private final EntityToDtoService entityToDtoService;

    public SeriesService(SeriesRepository seriesRepository,
                         ActorRepository actorRepository,
                         UserService userService,
                         GroupService groupService,
                         EntityToDtoService entityToDtoService) {
        this.seriesRepository = seriesRepository;
        this.actorRepository = actorRepository;
        this.userService = userService;
        this.groupService = groupService;
        this.entityToDtoService = entityToDtoService;
    }

    public boolean seriesEntityExists(long series_id) {
        return seriesRepository.existsById(series_id);
    }

    public SeriesGetRequest getSearchedSeries(long series_id) {
        return entityToDtoService.mapSeriesToDto(series_id);
    }

    public Series getSeriesEntity(long series_id) {
        if (seriesEntityExists(series_id)) {
            return seriesRepository.findById(series_id).get();
        } else {
            throw new MediaNotFoundException("Series does not exist");
        }
    }

    public void saveSeriesEntity(Series series) {
        seriesRepository.save(series);
    }

    public Iterable<Series> getAllSeriesEntities() { return seriesRepository.findAll(); }

    @Transactional
    public List<SeriesGetRequest> getSearchedSeries(String query) {
        Iterable<Series> foundSeries = seriesRepository.findSeriesByTitleContainsIgnoreCase(query);
        ArrayList<Series> toReturnSeries = new ArrayList<>();
        for (Series series : foundSeries) {
            toReturnSeries.add(series);
        }
        return entityToDtoService.mapListOfSeriesToDto(toReturnSeries);
    }
}
