package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.dto.NewUserPostRequestDto;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    private AvatarService avatarService;

    public UserService(@Lazy AvatarService avatarService) {
        this.avatarService = avatarService;
    }


    public String createUser(NewUserPostRequestDto newUserPostRequestDto) throws IOException {
        User user = new User();
        user.setUsername(newUserPostRequestDto.getUsername());
        user.setEmail(newUserPostRequestDto.getEmail());
        user.setEnabled(false);
        user.setAvatarUser(avatarService.setDefaultAvatarUser());
        userRepository.save(user);

        return user.getUsername();
    }

    public User getUser(long user_id) {
        return userRepository.findById(user_id).get();
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(long user_id) {
        User user = userRepository.findById(user_id).get();
        userRepository.delete(user);
    }

}
