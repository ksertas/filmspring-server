package nl.serkanertas.filmspringserver.repository;

import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.FilmRating;
import nl.serkanertas.filmspringserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRatingRepository extends JpaRepository<FilmRating, Long> {
     FilmRating findRatingByFilmAndUsername(Film film, User username);
}
