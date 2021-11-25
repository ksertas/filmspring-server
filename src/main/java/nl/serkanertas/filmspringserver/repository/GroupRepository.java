package nl.serkanertas.filmspringserver.repository;

import nl.serkanertas.filmspringserver.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {}
