package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Avatar;
import nl.serkanertas.filmspringserver.model.Group;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.AvatarRepository;
import nl.serkanertas.filmspringserver.repository.GroupRepository;
import nl.serkanertas.filmspringserver.repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
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

    public Avatar setDefaultAvatarUser() throws IOException {
        File file = new File("src/main/resources/static/img/default_pfp.png");
        FileInputStream fileInput = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("default_avatar", "default_pfp.png",
                "image/png", fileInput);

        Avatar avatar = new Avatar(
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                multipartFile.getBytes());

        return avatar;
    }

    public Avatar setDefaultAvatarGroup() throws IOException {
        File file = new File("src/main/resources/static/img/default_group.png");
        FileInputStream fileInput = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("default_group_avatar", "default_group.png",
                "image/png", fileInput);

        Avatar avatar = new Avatar(
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                multipartFile.getBytes());

        return avatar;
    }

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
        Group group = groupRepository.findById(group_id).get();
        Avatar avatar = new Avatar(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes());

        group.setAvatarGroup(avatar);
        groupRepository.save(group);
    }

    @Transactional
    public Avatar getAvatarGroup(long group_id) {
        Group group = groupRepository.findById(group_id).get();
        return group.getAvatarGroup();
    }

    public void deleteAvatarGroup(long group_id) {
        Group group = groupRepository.findById(group_id).get();
        Long currentAvatarId = group.getAvatarGroup().getAvatar_id();
        group.setAvatarGroup(null);
        avatarRepository.deleteById(currentAvatarId);
        groupRepository.save(group);}
}

