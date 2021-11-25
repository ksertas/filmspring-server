package nl.serkanertas.filmspringserver.repository;

import nl.serkanertas.filmspringserver.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {}
