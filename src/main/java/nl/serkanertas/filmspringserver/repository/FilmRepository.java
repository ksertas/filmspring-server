package nl.serkanertas.filmspringserver.repository;

import nl.serkanertas.filmspringserver.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Iterable<Film> findFIlmByTitleContainsIgnoreCase(@Param("s") String query);
}
