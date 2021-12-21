package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.dto.request.CreateUserPostRequest;
import nl.serkanertas.filmspringserver.dto.request.UpdateUserDetailsRequest;
import nl.serkanertas.filmspringserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/raw")
    ResponseEntity<Object> getAllUserEntities() {
        return ResponseEntity.ok().body(userService.getAllUsersEntities());
    }

    @GetMapping
    ResponseEntity<Object> getSearchedUsers(@RequestParam("search") String query) {
        return ResponseEntity.ok().body(userService.getSearchedUsers(query));
    }

    @GetMapping("/{user_id}")
    ResponseEntity<Object> getUser(@PathVariable String user_id) {
        return ResponseEntity.ok().body(userService.getSearchedUser(user_id));
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

    @PatchMapping("/{user_id}")
    ResponseEntity<Object> updateUser(@PathVariable String user_id, @Valid @RequestBody UpdateUserDetailsRequest updateDetailsDto) {
        userService.updateDetails(user_id, updateDetailsDto);
        return ResponseEntity.ok().body("Updated account.");
    }

    @DeleteMapping("/{user_id}")
    ResponseEntity<Object> deleteUser(@PathVariable String user_id) {
        userService.deleteUser(user_id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
