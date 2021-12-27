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
    private final SeriesService seriesService;

    public PosterService(PosterRepository posterRepository,
                         FilmService filmService,
                         SeriesService seriesService) {
        this.posterRepository = posterRepository;
        this.filmService = filmService;
        this.seriesService = seriesService;
    }

    @Transactional
    public void storePosterFilm(long film_id, MultipartFile file) throws IOException {
        try {
            Film film = filmService.getFilmEntity(film_id);
            Poster poster = new Poster(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes());

            film.setPosterFilm(poster);
            filmService.saveFilmEntity(film);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
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
        filmService.saveFilmEntity(film);
    }

    @Transactional
    public void storePosterSeries(long series_id, MultipartFile file) throws IOException {
        try {
            Series series = seriesService.getSeriesEntity(series_id);
            Poster poster = new Poster(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes());

            series.setPosterSeries(poster);
            seriesService.saveSeriesEntity(series);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
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
        seriesService.saveSeriesEntity(series);
    }
}
