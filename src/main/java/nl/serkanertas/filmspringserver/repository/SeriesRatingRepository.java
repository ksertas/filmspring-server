package nl.serkanertas.filmspringserver.repository;

import nl.serkanertas.filmspringserver.model.Series;
import nl.serkanertas.filmspringserver.model.SeriesRating;
import nl.serkanertas.filmspringserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRatingRepository extends JpaRepository<SeriesRating, Long> {
    SeriesRating findRatingBySeriesAndUsername(Series series, User username);
}
