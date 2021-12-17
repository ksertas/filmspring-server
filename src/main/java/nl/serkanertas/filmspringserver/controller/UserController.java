package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.dto.request.CreateUserPostRequest;
import nl.serkanertas.filmspringserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

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
    ResponseEntity<Object> createUser(@Valid @RequestBody CreateUserPostRequest newUserPostRequestDto, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Failed to register account");
        } else {
            userService.createUser(newUserPostRequestDto);
            return ResponseEntity.ok("created successfully");
        }
    }

    @DeleteMapping("/{user_id}")
    ResponseEntity<Object> deleteUser(@PathVariable long user_id) {
        userService.deleteUser(user_id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
