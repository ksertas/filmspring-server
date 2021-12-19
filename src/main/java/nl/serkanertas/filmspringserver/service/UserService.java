package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.dto.request.CreateUserPostRequest;
import nl.serkanertas.filmspringserver.dto.request.UpdateUserDetailsRequest;
import nl.serkanertas.filmspringserver.dto.response.SearchedUserGetRequest;
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

    public SearchedUserGetRequest mapUserToSearchedUser(Long user_id) {
        User user = userRepository.findById(user_id).get();
        SearchedUserGetRequest userDto = new SearchedUserGetRequest();
        userDto.setUsername(user.getUsername());
        userDto.setAvatar(user.getAvatarUser());
        if (!user.isMediaHidden()) {
            userDto.setWatchedFilms(user.getWatchedFilms());
            userDto.setWatchedSeries(user.getWatchedSeries());
            userDto.setPlannedFilms(user.getPlannedFlms());
            userDto.setPlannedSeries(user.getPlannedSeries());
        }
        userDto.setMediaHidden(user.isMediaHidden());
        return userDto;
    }

    public String createUser(CreateUserPostRequest userDto) throws IOException {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setEnabled(false);
        user.setMediaHidden(false);
        user.setAvatarUser(avatarService.setDefaultAvatarUser());
        userRepository.save(user);

        return user.getUsername();
    }

    public void updateDetails(Long user_id, UpdateUserDetailsRequest updateDetailsDto) {
        User user = getUserEntity(user_id);
        if (!(updateDetailsDto.getEmail() == null)) {
            user.setEmail(updateDetailsDto.getEmail());
        }
        // preference will be 'false' if not sent
        user.setMediaHidden(updateDetailsDto.isHideMediaPreference());

        userRepository.save(user);
    }

    public User getUserEntity(Long user_id) {
        return userRepository.findById(user_id).get();
    }

    public SearchedUserGetRequest getSearchedUser(long user_id) {
        return mapUserToSearchedUser(user_id);
    }

    public Iterable<User> getAllUsersEntity() {
        return userRepository.findAll();
    }

    public void deleteUser(long user_id) {
        User user = userRepository.findById(user_id).get();
        userRepository.delete(user);
    }

}
