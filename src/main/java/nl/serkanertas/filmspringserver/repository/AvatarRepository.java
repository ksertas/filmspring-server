package nl.serkanertas.filmspringserver.repository;

import nl.serkanertas.filmspringserver.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
