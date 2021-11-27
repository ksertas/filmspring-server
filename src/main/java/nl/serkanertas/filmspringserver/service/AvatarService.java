package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Avatar;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.AvatarRepository;
import nl.serkanertas.filmspringserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class AvatarService {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AvatarRepository avatarRepository;

    @Transactional
    public void storeAvatar(long user_id, MultipartFile file) throws IOException {
        User user = userService.getUser(user_id);
        Avatar avatar = new Avatar(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes());

        user.setAvatar(avatar);
        userRepository.save(user);
    }

    @Transactional
    public Avatar getAvatar(long user_id) {
        User user = userService.getUser(user_id);
        return user.getAvatar();
    }

    public void deleteAvatar(long user_id) {
        User user = userService.getUser(user_id);
        Long currentAvatarId = user.getAvatar().getAvatar_id();
        user.setAvatar(null);
        avatarRepository.deleteById(currentAvatarId);
        userRepository.save(user);
    }

}
