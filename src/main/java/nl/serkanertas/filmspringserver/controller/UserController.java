package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.service.AvatarService;
import nl.serkanertas.filmspringserver.service.FileStorageService;
import nl.serkanertas.filmspringserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    AvatarService avatarService;

    @GetMapping("/get/all")
    ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/create")
    ResponseEntity<Object> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @PutMapping("/upload/{user_id}/avatar")
    public ResponseEntity<Object> uploadAvatar(@RequestParam("file") MultipartFile file, @PathVariable long user_id) {
        try {
            avatarService.storeAvatar(user_id, file);
            return ResponseEntity.status(HttpStatus.OK).body("Success");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }
    }

    @GetMapping(value = "/get/{user_id}/avatar")
    public ResponseEntity<Object> getAvatar(@PathVariable long user_id) {
        return ResponseEntity.ok(avatarService.getAvatar(user_id));
    }

}
