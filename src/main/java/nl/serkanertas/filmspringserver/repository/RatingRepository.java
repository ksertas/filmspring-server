package nl.serkanertas.filmspringserver.repository;

import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Rating;
import nl.serkanertas.filmspringserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
     Rating findRatingByFilmAndUsername(Film film, User username);
}
