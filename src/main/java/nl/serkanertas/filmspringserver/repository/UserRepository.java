package nl.serkanertas.filmspringserver.repository;

import nl.serkanertas.filmspringserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {}
