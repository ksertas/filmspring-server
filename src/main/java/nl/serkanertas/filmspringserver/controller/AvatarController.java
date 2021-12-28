package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.models.AvatarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/avatars/")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

//    USERS

    @GetMapping("/users/{user_id}")
    public ResponseEntity<Object> getAvatarUser(@PathVariable String user_id) {
        return ResponseEntity.ok(avatarService.getAvatarUser(user_id));
    }

    @PutMapping("/users/{user_id}")
    @PreAuthorize("@postAuthService.isVerified() and @postAuthService.isCurrentUser(#user_id)")
    public ResponseEntity<Object> uploadAvatarUser(@RequestParam("file") MultipartFile file,
                                               @PathVariable String user_id) {
        try {
            avatarService.storeAvatarUser(user_id, file);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }
    }

    @DeleteMapping("/users/{user_id}")
    @PreAuthorize("@postAuthService.isVerified() and @postAuthService.isCurrentUser(#user_id)")
    public ResponseEntity<Object> deleteAvatarUser(@PathVariable String user_id) throws IOException {
        avatarService.deleteAvatarUser(user_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    GROUPS

    @GetMapping("/groups/{group_id}")
    public ResponseEntity<Object> getAvatarGroup(@PathVariable long group_id) {
        return ResponseEntity.ok(avatarService.getAvatarGroup(group_id));
    }

    @PutMapping("/groups/{group_id}")
    @PreAuthorize("@postAuthService.isGroupOwner(#group_id)")
    public ResponseEntity<Object> uploadAvatarGroup(@RequestParam("file") MultipartFile file,
                                               @PathVariable long group_id) {
        try {
            avatarService.storeAvatarGroup(group_id, file);
            return ResponseEntity.status(HttpStatus.OK).body("Success");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }
    }

    @DeleteMapping("/groups/{group_id}")
    @PreAuthorize("@postAuthService.isGroupOwner(#group_id)")
    public ResponseEntity<Object> deleteAvatarGroup(@PathVariable long group_id) throws IOException {
        avatarService.deleteAvatarGroup(group_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
