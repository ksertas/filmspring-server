package nl.serkanertas.filmspringserver.repository;

import nl.serkanertas.filmspringserver.model.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosterRepository extends JpaRepository<Poster, Long> {
}
