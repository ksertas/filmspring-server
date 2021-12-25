package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.dto.request.RatingDto;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.FilmRating;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.FilmRatingRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class FilmRatingService {
    private final UserService userService;
    private final FilmService filmService;
    private final FilmRatingRepository filmRatingRepository;

    public FilmRatingService(@Lazy UserService userService,
                             @Lazy FilmService filmService,
                             FilmRatingRepository filmRatingRepository) {
        this.userService = userService;
        this.filmService = filmService;
        this.filmRatingRepository = filmRatingRepository;
    }

    public void addFilmRating(String user_id, long film_id, RatingDto ratingDto) {
        int rating = ratingDto.getRating();
        User user = userService.getUserEntity(user_id);
        Film film = filmService.getFilmEntity(film_id);
        FilmRating filmRatingEntity = new FilmRating();
        filmRatingEntity.setUsername(user);
        filmRatingEntity.setFilm(film);
        filmRatingEntity.setRating(rating);
        filmRatingRepository.save(filmRatingEntity);
    }

    public int getFilmRating(String user_id, long film_id) {
        FilmRating filmRating = getFilmRatingEntity(user_id, film_id);
        if (filmRating != null) {
            return filmRating.getRating();
        }
        return 0;
    }

    public FilmRating getFilmRatingEntity(String user_id, long film_id) {
        User user = userService.getUserEntity(user_id);
        Film film = filmService.getFilmEntity(film_id);
        return filmRatingRepository.findRatingByFilmAndUsername(film, user);
    }

    public void deleteFilmRating(String user_id, long film_id) {
        FilmRating filmRating = getFilmRatingEntity(user_id, film_id);
        filmRatingRepository.delete(filmRating);
    }

    public void updateFilmRating(String user_id, long film_id, RatingDto ratingDto) {
        FilmRating filmRating = getFilmRatingEntity(user_id, film_id);
        filmRating.setRating(ratingDto.getRating());
        filmRatingRepository.save(filmRating);
    }
}
