package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Avatar;
import nl.serkanertas.filmspringserver.model.Group;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.AvatarRepository;
import nl.serkanertas.filmspringserver.repository.GroupRepository;
import nl.serkanertas.filmspringserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class AvatarService {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private AvatarRepository avatarRepository;

    @Transactional
    public void storeAvatarUser(long user_id, MultipartFile file) throws IOException {
        User user = userService.getUser(user_id);
        Avatar avatar = new Avatar(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes());

        user.setAvatarUser(avatar);
        userRepository.save(user);
    }

    @Transactional
    public Avatar getAvatarUser(long user_id) {
        User user = userService.getUser(user_id);
        return user.getAvatarUser();
    }

    public void deleteAvatarUser(long user_id) {
        User user = userService.getUser(user_id);
        Long currentAvatarId = user.getAvatarUser().getAvatar_id();
        user.setAvatarUser(null);
        avatarRepository.deleteById(currentAvatarId);
        userRepository.save(user);
    }

    @Transactional
    public void storeAvatarGroup(long group_id, MultipartFile file) throws IOException {
        Group group = groupService.getGroup(group_id);
        Avatar avatar = new Avatar(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes());

        group.setAvatarGroup(avatar);
        groupRepository.save(group);
    }

    @Transactional
    public Avatar getAvatarGroup(long group_id) {
        Group group = groupService.getGroup(group_id);
        return group.getAvatarGroup();
    }

    public void deleteAvatarGroup(long group_id) {
        Group group = groupService.getGroup(group_id);
        Long currentAvatarId = group.getAvatarGroup().getAvatar_id();
        group.setAvatarGroup(null);
        avatarRepository.deleteById(currentAvatarId);
        groupRepository.save(group);}
}

