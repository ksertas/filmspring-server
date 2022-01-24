package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.dto.request.CreateGroupPostRequest;
import nl.serkanertas.filmspringserver.dto.response.GroupGetRequest;
import nl.serkanertas.filmspringserver.dto.response.SearchedUserGetRequest;
import nl.serkanertas.filmspringserver.exception.GroupNotFoundException;
import nl.serkanertas.filmspringserver.exception.ResourceAlreadyExistsException;
import nl.serkanertas.filmspringserver.exception.UserNotFoundException;
import nl.serkanertas.filmspringserver.model.*;
import nl.serkanertas.filmspringserver.repository.GroupRepository;
import nl.serkanertas.filmspringserver.service.EntityToDtoService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserService userService;
    private final AvatarService avatarService;
    private final EntityToDtoService entityToDtoService;

    public GroupService(GroupRepository groupRepository,
                        UserService userService,
                        AvatarService avatarService,
                        @Lazy EntityToDtoService entityToDtoService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
        this.avatarService = avatarService;
        this.entityToDtoService = entityToDtoService;
    }

    public boolean groupEntityExists(long group_id) {
        return groupRepository.existsById(group_id);
    }

    public void saveGroupEntity(Group group) {
        groupRepository.save(group);
    }

    public void createGroup(CreateGroupPostRequest groupDto) throws IOException {
        try {
            Group group = new Group();
            group.setName(groupDto.getGroupName());
            group.setAvatarGroup(avatarService.setDefaultAvatarGroup());
            group.setWarned(false);
            groupRepository.save(group);
            String groupCreatorName = SecurityContextHolder.getContext().getAuthentication().getName();
            User groupCreator = userService.getUserEntity(groupCreatorName);
            group.setGroupOwnerName(groupCreatorName);
            groupCreator.addAuthority("ROLE_OWNER-GROUP-" + group.getGroup_id());
            userService.saveUserEntity(groupCreator);
            addUserToGroup(groupCreatorName, group.getGroup_id());
            saveGroupEntity(group);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public void deleteGroup(long group_id) {
        groupRepository.deleteById(group_id);
    }

    @Transactional
    public GroupGetRequest getGroup(long group_id) {
        return entityToDtoService.mapGroupToDto(group_id);
    }

    @Transactional
    public Group getGroupEntity(long group_id) {
        if (groupEntityExists(group_id)) {
            return groupRepository.findById(group_id).get();
        } else {
            throw new GroupNotFoundException("Group not found");
        }
    }

    @Transactional
    public List<GroupGetRequest> getAllGroups() {
        List<GroupGetRequest> groupDtos = new ArrayList<>();
        Iterable<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            groupDtos.add(entityToDtoService.mapGroupToDto(group.getGroup_id()));
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
        Group group = getGroupEntity(group_id);
        if (group.getUsersInGroup().contains(user)) {
            throw new ResourceAlreadyExistsException("User already in group");
        }
        group.getUsersInGroup().add(user);
        user.addAuthority("ROLE_MEMBER-GROUP-" + group.getGroup_id());
        saveGroupEntity(group);
    }

    @Transactional
    public void removeUserFromGroup(String user_id, long group_id) {
        User user = userService.getUserEntity(user_id);
        Group group = getGroupEntity(group_id);
        if (!(group.getUsersInGroup().contains(user))) {
            throw new UserNotFoundException("User not in group");
        }
        group.getUsersInGroup().remove(user);
        user.removeAuthority("ROLE_MEMBER-GROUP-" + group.getGroup_id());
        saveGroupEntity(group);
    }

}
