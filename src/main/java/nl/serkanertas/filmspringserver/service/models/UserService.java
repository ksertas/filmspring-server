package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.dto.request.CreateUserPostRequest;
import nl.serkanertas.filmspringserver.dto.request.UpdateUserDetailsRequest;
import nl.serkanertas.filmspringserver.dto.response.SearchedUserGetRequest;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.UserRepository;
import nl.serkanertas.filmspringserver.service.EntityToDtoService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService  {

    private final UserRepository userRepository;
    private final AvatarService avatarService;
    private final EntityToDtoService entityToDtoService;


    public UserService(@Lazy AvatarService avatarService,
                       UserRepository userRepository,
                       @Lazy EntityToDtoService entityToDtoService) {
        this.avatarService = avatarService;
        this.userRepository = userRepository;
        this.entityToDtoService = entityToDtoService;
    }

    public void createUser(CreateUserPostRequest userDto) throws IOException {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setEnabled(false);
        user.setMediaHidden(false);
        user.setAvatarUser(avatarService.setDefaultAvatarUser());
        userRepository.save(user);

    }

    public void updateDetails(String user_id, UpdateUserDetailsRequest updateDetailsDto) {
        User user = getUserEntity(user_id);
        if (!(updateDetailsDto.getEmail() == null)) {
            user.setEmail(updateDetailsDto.getEmail());
        }
        // preference will be 'false' if not sent
        user.setMediaHidden(updateDetailsDto.isHideMediaPreference());

        userRepository.save(user);
    }

    public User getUserEntity(String user_id) {
        return userRepository.findById(user_id).get();
    }

    public void saveUserEntity(User user) {
        userRepository.save(user);
    }

    public SearchedUserGetRequest getSearchedUser(String user_id) {
        return entityToDtoService.mapUserToSearchedUser(user_id);
    }

    @Transactional
    public List<SearchedUserGetRequest> getSearchedUsers(String query) {
        Iterable<User> users = userRepository.findUsersByUsernameContainsIgnoreCase(query);
        ArrayList<SearchedUserGetRequest> toReturnUsers = new ArrayList<>();
        for (User user : users) {
            toReturnUsers.add(entityToDtoService.mapUserToSearchedUser(user.getUsername()));
        }
        return toReturnUsers;
    }

    public Iterable<User> getAllUsersEntities() {
        return userRepository.findAll();
    }

    public void deleteUser(String user_id) {
        User user = userRepository.findById(user_id).get();
        userRepository.delete(user);
    }

}
