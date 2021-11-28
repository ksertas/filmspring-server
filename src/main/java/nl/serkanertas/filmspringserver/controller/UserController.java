package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{user_id}")
    ResponseEntity<Object> getUser(@PathVariable long user_id) {
        return ResponseEntity.ok().body(userService.getUser(user_id));
    }

    @PostMapping
    ResponseEntity<Object> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{user_id}")
    ResponseEntity<Object> deleteUser(@PathVariable long user_id) {
        userService.deleteUser(user_id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
