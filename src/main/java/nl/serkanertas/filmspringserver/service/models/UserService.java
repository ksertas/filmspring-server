package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.dto.request.CreateUserPostRequest;
import nl.serkanertas.filmspringserver.dto.request.UpdateUserDetailsRequest;
import nl.serkanertas.filmspringserver.dto.response.CurrentUserGetRequest;
import nl.serkanertas.filmspringserver.dto.response.SearchedUserGetRequest;
import nl.serkanertas.filmspringserver.exception.InvalidCredentialsException;
import nl.serkanertas.filmspringserver.exception.ResourceAlreadyExistsException;
import nl.serkanertas.filmspringserver.exception.UserNotFoundException;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.UserRepository;
import nl.serkanertas.filmspringserver.service.EntityToDtoService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;


    public UserService(@Lazy AvatarService avatarService,
                       UserRepository userRepository,
                       @Lazy EntityToDtoService entityToDtoService,
                       PasswordEncoder passwordEncoder) {
        this.avatarService = avatarService;
        this.userRepository = userRepository;
        this.entityToDtoService = entityToDtoService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean userEntityExists(String user_id) {
        return userRepository.existsById(user_id);
    }

    public String createUser(CreateUserPostRequest userDto) throws IOException {
           User user = new User();
           if (userRepository.existsByUsername(userDto.getUsername())) {
               throw new ResourceAlreadyExistsException("Username already exists");
           }
           if (userRepository.existsByEmail(userDto.getEmail())) {
               throw new ResourceAlreadyExistsException("Email already exists");
           }
           user.setUsername(userDto.getUsername());
           user.setFirstName(userDto.getFirstName());
           user.setLastName(userDto.getLastName());
           user.setEmail(userDto.getEmail());
           String bCryptPassword = passwordEncoder.encode(userDto.getPassword());
           user.setPassword(bCryptPassword);
           user.setEnabled(true);
           user.setVerified(true); // no SMTP server, so can't verify via email.
           user.setMediaHidden(false);
           try {
               user.setAvatarUser(avatarService.setDefaultAvatarUser());
           } catch (IOException e) {
               throw new IOException(e.getMessage());
           }
           user.addAuthority("ROLE_USER");
           saveUserEntity(user);
           return user.getUsername();
    }

    public void updateDetails(String user_id, UpdateUserDetailsRequest updateDetailsDto) {
        User user = getUserEntity(user_id);

        if (!(updateDetailsDto.getFirstName() == null)) {
            user.setFirstName(updateDetailsDto.getFirstName());
        }

        if (!(updateDetailsDto.getLastName() == null)) {
            user.setLastName(updateDetailsDto.getLastName());
        }

        if (!(updateDetailsDto.getBio() == null)) {
            user.setBio(updateDetailsDto.getBio());
        }

        if (!(updateDetailsDto.getEmail() == null)) {
            if (userRepository.existsByEmail(updateDetailsDto.getEmail())) {
                throw new ResourceAlreadyExistsException("Email already exists");
            }
            user.setEmail(updateDetailsDto.getEmail());
        }
        if (!(updateDetailsDto.getNewPassword() == null)) {
            String currentPassHash = user.getPassword();
            String oldPassRaw = updateDetailsDto.getOldPassword();
            String newPassRaw = updateDetailsDto.getNewPassword();
            if (passwordEncoder.matches(oldPassRaw, currentPassHash)) {
                String bCryptPassword = passwordEncoder.encode(newPassRaw);
                user.setPassword(bCryptPassword);
            } else {
                throw new InvalidCredentialsException("Current password is invalid");
            }
        }

        user.setMediaHidden(updateDetailsDto.isHideMediaPreference());

        saveUserEntity(user);
    }

    public User getUserEntity(String user_id) {
        if (userEntityExists(user_id)) {
            return userRepository.findById(user_id).get();
        } else throw new UserNotFoundException("User " + user_id + " not found");
    }

    public void saveUserEntity(User user) {
        userRepository.save(user);
    }

    public CurrentUserGetRequest getCurrentUser(String user_id) {
        return entityToDtoService.mapUserToCurrentUser(user_id);
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
        User user = getUserEntity(user_id);
        userRepository.delete(user);
    }

}
