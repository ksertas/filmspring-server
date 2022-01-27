package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.exception.BadRequestException;
import nl.serkanertas.filmspringserver.model.Authority;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.service.models.GroupService;
import nl.serkanertas.filmspringserver.service.models.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InviteService {

    private final UserService userService;
    private final GroupService groupService;

    public InviteService(UserService userService,
                         GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    public boolean checkIfAuthorityExists(String user_id, String authority) {
        User user = userService.getUserEntity(user_id);
        for (Authority auth : user.getAuthorities()) {
            if (auth.getAuthority().equals(authority)) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void inviteUser(String user_id, long group_id) {
        User user = userService.getUserEntity(user_id);
        String searchedAuthority = "ROLE_INVITED-" + group_id;
        if (!checkIfAuthorityExists(user_id, searchedAuthority)) {
            user.addAuthority(searchedAuthority);
            user.getGroupInvitationIds().add(Long.toString(group_id));
            userService.saveUserEntity(user);
        } else {
            throw new BadRequestException("User is already invited");
        }
    }

    @Transactional
    public void acceptInvite(String user_id, long group_id) {
        User user = userService.getUserEntity(user_id);
        String searchInviteRole = "ROLE_INVITED-" + group_id;
        groupService.addUserToGroup(user_id, group_id);
        user.removeAuthority(searchInviteRole);
        user.getGroupInvitationIds().remove(Long.toString(group_id));
        userService.saveUserEntity(user);
    }

    @Transactional
    public void rejectInvite(String user_id, long group_id) {
        User user = userService.getUserEntity(user_id);
        String searchInviteRole = "ROLE_INVITED-" + group_id;
        user.removeAuthority(searchInviteRole);
        user.getGroupInvitationIds().remove(Long.toString(group_id));
        userService.saveUserEntity(user);
    }
}
