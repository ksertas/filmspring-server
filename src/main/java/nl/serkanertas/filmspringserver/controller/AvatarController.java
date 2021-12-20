package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/avatars/")
public class AvatarController {

    @Autowired
    AvatarService avatarService;

//    USERS

    @GetMapping("/users/{user_id}")
    public ResponseEntity<Object> getAvatarUser(@PathVariable String user_id) {
        return ResponseEntity.ok(avatarService.getAvatarUser(user_id));
    }

    @PutMapping("/users/{user_id}")
    public ResponseEntity<Object> uploadAvatarUser(@RequestParam("file") MultipartFile file,
                                               @PathVariable String user_id) {
        try {
            avatarService.storeAvatarUser(user_id, file);
            return ResponseEntity.status(HttpStatus.OK).body("Success");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }
    }

    @DeleteMapping("/users/{user_id}")
    public ResponseEntity<Object> deleteAvatarUser(@PathVariable String user_id) {
        avatarService.deleteAvatarUser(user_id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

//    GROUPS

    @GetMapping("/groups/{group_id}")
    public ResponseEntity<Object> getAvatarGroup(@PathVariable long group_id) {
        return ResponseEntity.ok(avatarService.getAvatarGroup(group_id));
    }

    @PutMapping("/groups/{group_id}")
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
    public ResponseEntity<Object> deleteAvatarGroup(@PathVariable long group_id) {
        avatarService.deleteAvatarGroup(group_id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
