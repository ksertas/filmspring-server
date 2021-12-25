package nl.serkanertas.filmspringserver.repository;

import nl.serkanertas.filmspringserver.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
