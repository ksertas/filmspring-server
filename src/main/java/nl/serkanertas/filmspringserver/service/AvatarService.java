package nl.serkanertas.filmspringserver.service;

import antlr.StringUtils;
import nl.serkanertas.filmspringserver.model.Avatar;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.AvatarRepository;
import nl.serkanertas.filmspringserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class AvatarService {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AvatarRepository avatarRepository;

    @Transactional
    public User storeAvatar(long user_id, MultipartFile file) throws IOException {
        User user = userService.getUser(user_id);
        String name = file.getOriginalFilename();
        Avatar avatar = new Avatar(name,
                file.getContentType(),
                file.getBytes());
        System.out.println(avatar);
        user.setAvatar(avatar);
        avatarRepository.save(avatar);
        return userRepository.save(user);
    }

    @Transactional
    public Avatar getAvatar(long avatar_id) {
//        User user = userService.getUser(user_id);
        Avatar avatar = avatarRepository.findById(avatar_id).get();
        System.out.println(avatar.getData().length);
        return avatar;
    }

}
