package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Group;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    public void createGroup(Group group) {
        groupRepository.save(group);
    }

    public void deleteGroup(long group_id) {
        groupRepository.deleteById(group_id);
    }

    public Group getGroup(long group_id) {
        return groupRepository.findById(group_id).get();
    }

    public Iterable<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Iterable<User> getAllUsersFromGroup(long group_id) {
        return getGroup(group_id).getUsersInGroup();
    }

    @Transactional
    public void addUserToGroup(long user_id, long group_id) {
        User user = userService.getUser(user_id);
        Group group = getGroup(group_id);
        group.getUsersInGroup().add(user);
        groupRepository.save(group);
    }

    public void removeUserFromGroup(long user_id, long group_id) {
        User user = userService.getUser(user_id);
        Group group = getGroup(group_id);
        group.getUsersInGroup().remove(user);
        groupRepository.save(group);
    }

}
