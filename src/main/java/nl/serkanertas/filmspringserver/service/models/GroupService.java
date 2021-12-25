package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.dto.request.CreateGroupPostRequest;
import nl.serkanertas.filmspringserver.dto.response.GroupGetRequest;
import nl.serkanertas.filmspringserver.dto.response.SearchedUserGetRequest;
import nl.serkanertas.filmspringserver.model.*;
import nl.serkanertas.filmspringserver.repository.GroupInvitationRepository;
import nl.serkanertas.filmspringserver.repository.GroupRepository;
import nl.serkanertas.filmspringserver.repository.UserRepository;
import nl.serkanertas.filmspringserver.service.EntityToDtoService;
import nl.serkanertas.filmspringserver.service.PostAuthService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupInvitationRepository groupInvitationRepository;
    private final UserService userService;
    private final AvatarService avatarService;
    private final EntityToDtoService entityToDtoService;
    private final PostAuthService postAuthService;

    public GroupService(GroupRepository groupRepository,
                        UserRepository userRepository,
                        GroupInvitationRepository groupInvitationRepository,
                        UserService userService,
                        AvatarService avatarService,
                        @Lazy EntityToDtoService entityToDtoService,
                        @Lazy PostAuthService postAuthService) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.groupInvitationRepository = groupInvitationRepository;
        this.userService = userService;
        this.avatarService = avatarService;
        this.entityToDtoService = entityToDtoService;
        this.postAuthService = postAuthService;
    }

    public void createGroup(CreateGroupPostRequest groupDto) throws IOException {
        Group group = new Group();
        group.setName(groupDto.getGroupName());
        group.setAvatarGroup(avatarService.setDefaultAvatarGroup());
        group.setWarned(false);
        groupRepository.save(group);
        String groupCreatorName = SecurityContextHolder.getContext().getAuthentication().getName();
        User groupCreator = userService.getUserEntity(groupCreatorName);
        groupCreator.addAuthority("ROLE_OWNER-GROUP-" + group.getGroup_id());
        userService.saveUserEntity(groupCreator);
        addUserToGroup(groupCreatorName, group.getGroup_id());
        groupRepository.save(group);
    }

    public void deleteGroup(long group_id) {
        groupRepository.deleteById(group_id);
    }

    @Transactional
    @PreAuthorize("@postAuthService.isGroupOwner(#group_id)")
    public GroupGetRequest getGroup(long group_id) {
        return entityToDtoService.mapGroupToDto(group_id);
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

}
