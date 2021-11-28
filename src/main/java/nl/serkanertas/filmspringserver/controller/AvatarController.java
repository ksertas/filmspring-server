package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users/avatar")
public class AvatarController {

    @Autowired
    AvatarService avatarService;

    @GetMapping(value = "/{user_id}")
    public ResponseEntity<Object> getAvatar(@PathVariable long user_id) {
        return ResponseEntity.ok(avatarService.getAvatar(user_id));
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<Object> uploadAvatar(@RequestParam("file") MultipartFile file, @PathVariable long user_id) {
        try {
            avatarService.storeAvatar(user_id, file);
            return ResponseEntity.status(HttpStatus.OK).body("Success");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Object> deleteAvatar(@PathVariable long user_id) {
        avatarService.deleteAvatar(user_id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
