package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.dto.request.CreateUserPostRequest;
import nl.serkanertas.filmspringserver.dto.request.UpdateUserDetailsRequest;
import nl.serkanertas.filmspringserver.service.PlanMediaService;
import nl.serkanertas.filmspringserver.service.WatchMediaService;
import nl.serkanertas.filmspringserver.service.models.FilmService;
import nl.serkanertas.filmspringserver.service.models.SeriesService;
import nl.serkanertas.filmspringserver.service.models.UserService;
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
    private final WatchMediaService watchMediaService;
    private final PlanMediaService planMediaService;

    public UserController(UserService userService,
                          WatchMediaService watchMediaService,
                          PlanMediaService planMediaService) {
        this.userService = userService;
        this.watchMediaService = watchMediaService;
        this.planMediaService = planMediaService;
    }

    @GetMapping
    ResponseEntity<Object> getSearchedUsers(@RequestParam("search") String query) {
        return ResponseEntity.ok().body(userService.getSearchedUsers(query));
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

    @GetMapping("/raw")
    ResponseEntity<Object> getAllUserEntities() {
        return ResponseEntity.ok().body(userService.getAllUsersEntities());
    }

    @GetMapping("/{user_id}")
    ResponseEntity<Object> getUser(@PathVariable String user_id) {
        return ResponseEntity.ok().body(userService.getSearchedUser(user_id));
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
