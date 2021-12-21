package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.model.*;
import nl.serkanertas.filmspringserver.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class PosterService {
    private final PosterRepository posterRepository;
    private final FilmService filmService;
    private final FilmRepository filmRepository;
    private final SeriesService seriesService;
    private final SeriesRepository seriesRepository;

    public PosterService(PosterRepository posterRepository,
                         FilmService filmService,
                         FilmRepository filmRepository,
                         SeriesService seriesService,
                         SeriesRepository seriesRepository) {
        this.posterRepository = posterRepository;
        this.filmService = filmService;
        this.filmRepository = filmRepository;
        this.seriesService = seriesService;
        this.seriesRepository = seriesRepository;
    }


    @Transactional
    public void storePosterFilm(long film_id, MultipartFile file) throws IOException {
        Film film = filmService.getFilmEntity(film_id);
        Poster poster = new Poster(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes());

        film.setPosterFilm(poster);
        filmRepository.save(film);
    }

    @Transactional
    public Poster getPosterFilm(long film_id) {
        Film film = filmService.getFilmEntity(film_id);
        return film.getPosterFilm();
    }

    public void deletePosterFilm(long film_id) {
        Film film = filmService.getFilmEntity(film_id);
        Long currentPosterId = film.getPosterFilm().getPoster_id();
        film.setPosterFilm(null);
        posterRepository.deleteById(currentPosterId);
        filmRepository.save(film);
    }

    @Transactional
    public void storePosterSeries(long series_id, MultipartFile file) throws IOException {
        Series series = seriesService.getSeriesEntity(series_id);
        Poster poster = new Poster(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes());

        series.setPosterSeries(poster);
        seriesRepository.save(series);
    }

    @Transactional
    public Poster getPosterSeries(long series_id) {
        Series series = seriesService.getSeriesEntity(series_id);
        return series.getPosterSeries();
    }

    public void deletePosterSeries(long series_id) {
        Series series = seriesService.getSeriesEntity(series_id);
        Long currentPosterId = series.getPosterSeries().getPoster_id();
        series.setPosterSeries(null);
        posterRepository.deleteById(currentPosterId);
        seriesRepository.save(series);
    }
}
