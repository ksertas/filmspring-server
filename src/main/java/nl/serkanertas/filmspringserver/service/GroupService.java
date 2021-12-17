package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.dto.request.GroupPostRequestDto;
import nl.serkanertas.filmspringserver.dto.response.GroupGetRequestDto;
import nl.serkanertas.filmspringserver.dto.response.SearchedUserGetRequestDto;
import nl.serkanertas.filmspringserver.model.Group;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    public void createGroup(GroupPostRequestDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getGroupName());
        group.setAvatarGroup(groupDto.getGroupAvatar());
        group.setWarned(false);
        groupRepository.save(group);
    }

    public GroupGetRequestDto mapGroupToDto(Long group_id){
        Group group = groupRepository.findById(group_id).get();
        GroupGetRequestDto groupDto = new GroupGetRequestDto();
        ArrayList<SearchedUserGetRequestDto> userGroupList = new ArrayList<>();
        groupDto.setGroupName(group.getName());
        groupDto.setAvatar(group.getAvatarGroup());

        for (User user : group.getUsersInGroup()) {
            userGroupList.add(userService.getSearchedUser(user.getUser_id()));
        }
        groupDto.setUsersInGroup(userGroupList);
        return groupDto;
    }


    public void deleteGroup(long group_id) {
        groupRepository.deleteById(group_id);
    }

    @Transactional
    public GroupGetRequestDto getGroup(long group_id) {
        return mapGroupToDto(group_id);
    }

    @Transactional
    public List<GroupGetRequestDto> getAllGroups() {
        List<GroupGetRequestDto> groupDtos = new ArrayList<>();
        Iterable<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            groupDtos.add(mapGroupToDto(group.getGroup_id()));
        }
        return groupDtos;
    }

    @Transactional
    public Iterable<SearchedUserGetRequestDto> getAllUsersFromGroup(long group_id) {
        return getGroup(group_id).getUsersInGroup();
    }

    @Transactional
    public void addUserToGroup(long user_id, long group_id) {
        User user = userService.getUser(user_id);
        Group group = groupRepository.findById(group_id).get();
        group.getUsersInGroup().add(user);
        groupRepository.save(group);
    }

    public void removeUserFromGroup(long user_id, long group_id) {
        User user = userService.getUser(user_id);
        Group group = groupRepository.findById(group_id).get();
        group.getUsersInGroup().remove(user);
        groupRepository.save(group);
    }

}
