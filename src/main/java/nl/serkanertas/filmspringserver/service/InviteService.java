package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Authority;
import nl.serkanertas.filmspringserver.model.GroupInvitation;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.service.models.GroupInvitationService;
import nl.serkanertas.filmspringserver.service.models.GroupService;
import nl.serkanertas.filmspringserver.service.models.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InviteService {

    private final UserService userService;
    private final GroupService groupService;
    private final GroupInvitationService groupInvitationService;

    public InviteService(UserService userService,
                         GroupService groupService,
                         GroupInvitationService groupInvitationService) {
        this.userService = userService;
        this.groupService = groupService;
        this.groupInvitationService = groupInvitationService;
    }

    @Transactional
    public void inviteUser(String user_id, long group_id) {
        User user = userService.getUserEntity(user_id);
        user.addAuthority("ROLE_INVITED-" + group_id);
        userService.saveUserEntity(user);
    }

    @Transactional
    public void acceptInvite(String user_id, long group_id) {
        User user = userService.getUserEntity(user_id);
        String searchInviteRole = "ROLE_INVITED-" + group_id;
        groupService.addUserToGroup(user_id, group_id);
        user.removeAuthority(searchInviteRole);
        userService.saveUserEntity(user);
    }

    @Transactional
    public void rejectInvite(String user_id, long group_id) {
        User user = userService.getUserEntity(user_id);
        String searchInviteRole = "ROLE_INVITED-" + group_id;
        user.removeAuthority(searchInviteRole);
        userService.saveUserEntity(user);
    }
}
