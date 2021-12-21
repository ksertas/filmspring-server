package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.model.GroupInvitation;
import nl.serkanertas.filmspringserver.repository.GroupInvitationRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupInvitationService {
    private final GroupInvitationRepository groupInvitationRepository;

    public GroupInvitationService(GroupInvitationRepository groupInvitationRepository) {
        this.groupInvitationRepository = groupInvitationRepository;
    }

    public GroupInvitation getGroupInvitationEntity(long invitation_id) {
        return groupInvitationRepository.findById(invitation_id).get();
    }

    public void saveGroupInvitationEntity(GroupInvitation invitation) {
        groupInvitationRepository.save(invitation);
    }

    public void deleteGroupInvitationEntity(long invitation_id) {
        groupInvitationRepository.deleteById(invitation_id);
    }
}
