package nl.serkanertas.filmspringserver.repository;

import nl.serkanertas.filmspringserver.model.GroupInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupInvitationRepository extends JpaRepository<GroupInvitation, Long> {
}
