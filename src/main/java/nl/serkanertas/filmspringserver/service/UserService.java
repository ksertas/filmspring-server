package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

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
