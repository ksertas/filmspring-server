package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.dto.request.CreateUserPostRequest;
import nl.serkanertas.filmspringserver.dto.request.UpdateUserDetailsRequest;
import nl.serkanertas.filmspringserver.service.PostAuthService;
import nl.serkanertas.filmspringserver.service.models.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PostAuthService postAuthService;

    public UserController(UserService userService, PostAuthService postAuthService) {
        this.userService = userService;
        this.postAuthService = postAuthService;
    }

    @GetMapping("/{user_id}")
    @PreAuthorize("hasRole(\"ROLE_USER\")")
    ResponseEntity<Object> getUser(@PathVariable String user_id) {
        if (postAuthService.isCurrentUser(user_id)) {
            return ResponseEntity.ok().body(userService.getCurrentUser(user_id));
        }
        return ResponseEntity.ok().body(userService.getSearchedUser(user_id));
    }

    @PostMapping
    @PreAuthorize("hasRole(\"ROLE_ANONYMOUS\")")
    ResponseEntity<Object> createNewUser(@Valid @RequestBody CreateUserPostRequest
                                              newUserPostRequestDto, BindingResult result)
            throws IOException {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Failed to register account");
        } else {
            userService.createUser(newUserPostRequestDto);
            return ResponseEntity.ok("created successfully");
        }
    }

    @PatchMapping("/{user_id}/account")
    @PreAuthorize("@postAuthService.isCurrentUser(#user_id)")
    ResponseEntity<Object> updateCurrentUser(@PathVariable String user_id, @Valid @RequestBody UpdateUserDetailsRequest updateDetailsDto) {
        userService.updateDetails(user_id, updateDetailsDto);
        return ResponseEntity.ok().body("Updated account.");
    }

    @DeleteMapping("/{user_id}/account")
    @PreAuthorize("@postAuthService.isCurrentUser(#user_id) or hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> deleteCurrentUser(@PathVariable String user_id) {
        userService.deleteUser(user_id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

//    logged in only
    @GetMapping
    @PreAuthorize("hasRole(\"ROLE_USER\")")
    ResponseEntity<Object> getSearchedUsers(@RequestParam("search") String query) {
        return ResponseEntity.ok().body(userService.getSearchedUsers(query));
    }

//    admin only
    @GetMapping("/raw")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> getAllUserEntities() {
        return ResponseEntity.ok().body(userService.getAllUsersEntities());
    }

}
