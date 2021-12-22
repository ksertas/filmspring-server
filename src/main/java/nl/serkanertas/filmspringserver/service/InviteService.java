package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.GroupInvitation;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.service.models.GroupInvitationService;
import nl.serkanertas.filmspringserver.service.models.GroupService;
import nl.serkanertas.filmspringserver.service.models.UserService;
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
        GroupInvitation groupInvitation = new GroupInvitation();
        groupInvitation.setGroup_id(group_id);
        groupInvitationService.saveGroupInvitationEntity(groupInvitation);
        user.getGroupInvitations().add(groupInvitation);
        userService.saveUserEntity(user);
    }

    @Transactional
    public void acceptInvite(String user_id, long group_id) {
        User user = userService.getUserEntity(user_id);
        boolean isAdded = false;
        GroupInvitation foundGroup = null;

        for (GroupInvitation group : user.getGroupInvitations()) {
            if (group.getGroup_id() == group_id) {
                groupService.addUserToGroup(user_id, group_id);
                userService.saveUserEntity(user);
                // needed to avoid ConcurrentModificationException
                isAdded = true;
                foundGroup = group;
                break;
            }
        }
        if (isAdded) {
            user.getGroupInvitations().remove(foundGroup);
            groupInvitationService.deleteGroupInvitationEntity(foundGroup.getInvite_id());
        }
    }

    @Transactional
    public void rejectInvite(String user_id, long group_id) {
        User user = userService.getUserEntity(user_id);

        for (GroupInvitation group : user.getGroupInvitations()) {
            if (group.getGroup_id() == group_id) {
                GroupInvitation foundGroup = group;
                user.getGroupInvitations().remove(foundGroup);
                groupInvitationService.deleteGroupInvitationEntity(foundGroup.getInvite_id());
                System.out.println("Removed invite successfully");
                break;
            }
        }
    }
}
