package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.model.Avatar;
import nl.serkanertas.filmspringserver.model.Group;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.AvatarRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class AvatarService {
    private final UserService userService;
    private final GroupService groupService;
    private final AvatarRepository avatarRepository;

    public AvatarService(UserService userService,
                         @Lazy GroupService groupService,
                         AvatarRepository avatarRepository) {
        this.userService = userService;
        this.groupService = groupService;
        this.avatarRepository = avatarRepository;
    }

    public Avatar setDefaultAvatarUser() throws IOException {
      try {
          File file = new File("src/main/resources/static/img/default_pfp.png");
          FileInputStream fileInput = new FileInputStream(file);
          MultipartFile multipartFile = new MockMultipartFile("default_avatar", "default_pfp.png",
                  "image/png", fileInput);

          return new Avatar(
                  multipartFile.getOriginalFilename(),
                  multipartFile.getContentType(),
                  multipartFile.getBytes());
      } catch (IOException e) {
          throw new IOException(e.getMessage());
      }
    }

    public Avatar setDefaultAvatarGroup() throws IOException {
        try {
            File file = new File("src/main/resources/static/img/default_group.png");
            FileInputStream fileInput = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("default_group_avatar", "default_group.png",
                    "image/png", fileInput);

            return new Avatar(
                    multipartFile.getOriginalFilename(),
                    multipartFile.getContentType(),
                    multipartFile.getBytes());
        }
        catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Transactional
    public void storeAvatarUser(String user_id, MultipartFile file) throws IOException {
        try {
            User user = userService.getUserEntity(user_id);
            Avatar avatar = new Avatar(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes());

            user.setAvatarUser(avatar);
            userService.saveUserEntity(user);
        }
        catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Transactional
    public Avatar getAvatarUser(String user_id) {
        User user = userService.getUserEntity(user_id);
        return user.getAvatarUser();
    }

    public void deleteAvatarUser(String user_id) throws IOException {
        User user = userService.getUserEntity(user_id);
        Long currentAvatarId = user.getAvatarUser().getAvatar_id();
        user.setAvatarUser(setDefaultAvatarUser());
        avatarRepository.deleteById(currentAvatarId);
        userService.saveUserEntity(user);
    }

    @Transactional
    public void storeAvatarGroup(long group_id, MultipartFile file) throws IOException {
        try {
            Group group = groupService.getGroupEntity(group_id);
            Avatar avatar = new Avatar(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes());

            group.setAvatarGroup(avatar);
            groupService.saveGroupEntity(group);
        }
        catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Transactional
    public Avatar getAvatarGroup(long group_id) {
        Group group = groupService.getGroupEntity(group_id);
        return group.getAvatarGroup();
    }

    public void deleteAvatarGroup(long group_id) throws IOException {
        Group group = groupService.getGroupEntity(group_id);
        Long currentAvatarId = group.getAvatarGroup().getAvatar_id();
        group.setAvatarGroup(setDefaultAvatarGroup());
        avatarRepository.deleteById(currentAvatarId);
        groupService.saveGroupEntity(group);
    }
}

