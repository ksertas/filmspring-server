package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.*;
import nl.serkanertas.filmspringserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class PosterService {

    @Autowired
    private PosterRepository posterRepository;

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private SeriesRepository seriesRepository;

    @Transactional
    public void storePosterFilm(long film_id, MultipartFile file) throws IOException {
        Film film = filmService.getFilm(film_id);
        Poster poster = new Poster(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes());

        film.setPosterFilm(poster);
        filmRepository.save(film);
    }

    @Transactional
    public Poster getPosterFilm(long film_id) {
        Film film = filmService.getFilm(film_id);
        return film.getPosterFilm();
    }

    public void deletePosterFilm(long film_id) {
        Film film = filmService.getFilm(film_id);
        Long currentPosterId = film.getPosterFilm().getPoster_id();
        film.setPosterFilm(null);
        posterRepository.deleteById(currentPosterId);
        filmRepository.save(film);
    }

    @Transactional
    public void storePosterSeries(long series_id, MultipartFile file) throws IOException {
        Series series = seriesService.getSeries(series_id);
        Poster poster = new Poster(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes());

        series.setPosterSeries(poster);
        seriesRepository.save(series);
    }

    @Transactional
    public Poster getPosterSeries(long series_id) {
        Series series = seriesService.getSeries(series_id);
        return series.getPosterSeries();
    }

    public void deletePosterSeries(long series_id) {
        Series series = seriesService.getSeries(series_id);
        Long currentPosterId = series.getPosterSeries().getPoster_id();
        series.setPosterSeries(null);
        posterRepository.deleteById(currentPosterId);
        seriesRepository.save(series);
    }
}
