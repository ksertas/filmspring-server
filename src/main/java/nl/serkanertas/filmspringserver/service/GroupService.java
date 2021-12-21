package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.dto.request.CreateGroupPostRequest;
import nl.serkanertas.filmspringserver.dto.response.GroupGetRequest;
import nl.serkanertas.filmspringserver.dto.response.SearchedUserGetRequest;
import nl.serkanertas.filmspringserver.model.Group;
import nl.serkanertas.filmspringserver.model.GroupInvitation;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.GroupInvitationRepository;
import nl.serkanertas.filmspringserver.repository.GroupRepository;
import nl.serkanertas.filmspringserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupInvitationRepository groupInvitationRepository;

    @Autowired
    private UserService userService;

    private AvatarService avatarService;

    public GroupService(@Lazy AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    public GroupGetRequest mapGroupToDto(Long group_id){
        Group group = groupRepository.findById(group_id).get();
        GroupGetRequest groupDto = new GroupGetRequest();
        ArrayList<SearchedUserGetRequest> userGroupList = new ArrayList<>();
        groupDto.setGroupName(group.getName());
        groupDto.setAvatar(group.getAvatarGroup());

        for (User user : group.getUsersInGroup()) {
            userGroupList.add(userService.getSearchedUser(user.getUsername()));
        }
        groupDto.setUsersInGroup(userGroupList);
        groupDto.setPlannedFilms(group.getPlannedFlms());
        groupDto.setPlannedSeries(group.getPlannedSeries());
        return groupDto;
    }

    public void createGroup(CreateGroupPostRequest groupDto) throws IOException {
        Group group = new Group();
        group.setName(groupDto.getGroupName());
        group.setAvatarGroup(avatarService.setDefaultAvatarGroup());
        group.setWarned(false);
        groupRepository.save(group);
    }

    public void deleteGroup(long group_id) {
        groupRepository.deleteById(group_id);
    }

    @Transactional
    public GroupGetRequest getGroup(long group_id) {
        return mapGroupToDto(group_id);
    }

    @Transactional
    public Group getGroupEntity(long group_id) {
        return groupRepository.findById(group_id).get();
    }

    @Transactional
    public List<GroupGetRequest> getAllGroups() {
        List<GroupGetRequest> groupDtos = new ArrayList<>();
        Iterable<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            groupDtos.add(mapGroupToDto(group.getGroup_id()));
        }
        return groupDtos;
    }

    @Transactional
    public Iterable<SearchedUserGetRequest> getAllUsersFromGroup(long group_id) {
        return getGroup(group_id).getUsersInGroup();
    }

    @Transactional
    public void addUserToGroup(String user_id, long group_id) {
        User user = userService.getUserEntity(user_id);
        Group group = groupRepository.findById(group_id).get();
        group.getUsersInGroup().add(user);
        groupRepository.save(group);
    }

    @Transactional
    public void removeUserFromGroup(String user_id, long group_id) {
        User user = userService.getUserEntity(user_id);
        Group group = groupRepository.findById(group_id).get();
        group.getUsersInGroup().remove(user);
        groupRepository.save(group);
    }

    @Transactional
    public void inviteUser(String user_id, long group_id) {
        User user = userService.getUserEntity(user_id);
        GroupInvitation groupInvitation = new GroupInvitation();
        groupInvitation.setGroup_id(group_id);
        groupInvitationRepository.save(groupInvitation);
        user.getGroupInvitations().add(groupInvitation);
        userRepository.save(user);
    }

    @Transactional
    public void acceptInvite(String user_id, long group_id) {
        User user = userService.getUserEntity(user_id);
//        List<GroupInvitation> copyGI = user.getGroupInvitations();
        boolean isAdded = false;
        GroupInvitation foundGroup = null;

        for (GroupInvitation group : user.getGroupInvitations()) {
            if (group.getGroup_id() == group_id) {
                System.out.println("above add user");
                addUserToGroup(user_id, group_id);
                System.out.println("above save user");
                userRepository.save(user);
                isAdded = true;
                foundGroup = group;
                System.out.println("last line of 1st if statement");
                break;
            }
        }
                System.out.println("outside 2nd if statement");
            if (isAdded) {
                System.out.println("above user.getinvites remove found group");
                user.getGroupInvitations().remove(foundGroup);
                System.out.println("above delete from repo");
                groupInvitationRepository.deleteById(foundGroup.getInvite_id());
            }
    }
}
