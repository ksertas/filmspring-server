package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.dto.request.RatingDto;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Rating;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.RatingRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    private final UserService userService;
    private final FilmService filmService;
    private final RatingRepository ratingRepository;

    public RatingService(@Lazy UserService userService,
                         @Lazy FilmService filmService,
                         RatingRepository ratingRepository) {
        this.userService = userService;
        this.filmService = filmService;
        this.ratingRepository = ratingRepository;
    }

    public void addFilmRating(String user_id, long film_id, RatingDto ratingDto) {
        int rating = ratingDto.getRating();
        User user = userService.getUserEntity(user_id);
        Film film = filmService.getFilmEntity(film_id);
        Rating ratingEntity = new Rating();
        ratingEntity.setUsername(user);
        ratingEntity.setFilm(film);
        ratingEntity.setRating(rating);
        ratingRepository.save(ratingEntity);
    }

    public int getFilmRating(String user_id, long film_id) {
        User user = userService.getUserEntity(user_id);
        Film film = filmService.getFilmEntity(film_id);
        Rating rating = ratingRepository.findRatingByFilmAndUsername(film, user);
        return rating.getRating();
    }
}
