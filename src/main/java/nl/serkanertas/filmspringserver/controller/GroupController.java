package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.dto.request.CreateGroupPostRequest;
import nl.serkanertas.filmspringserver.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    ResponseEntity<Object> getAllGroups() {
        return ResponseEntity.ok().body(groupService.getAllGroups());
    }

    @GetMapping("/{group_id}")
    ResponseEntity<Object> getGroup(@PathVariable("group_id") long group_id) {
        return ResponseEntity.ok().body(groupService.getGroup(group_id));
    }

    @PostMapping
    ResponseEntity<Object> createGroup(@Valid @RequestBody CreateGroupPostRequest group) {
        groupService.createGroup(group);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{group_id}")
    ResponseEntity<Object> deleteGroup(@PathVariable("group_id") long group_id) {
        groupService.deleteGroup(group_id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{group_id}/users")
    ResponseEntity<Object> getAllUsersFromGroup(@PathVariable("group_id") long group_id) {
        return ResponseEntity.ok().body(groupService.getAllUsersFromGroup(group_id));
    }

    @PutMapping("/{group_id}/users/{user_id}")
    ResponseEntity<Object> addUserToGroup(@PathVariable("group_id") long group_id,
                                          @PathVariable("user_id") long user_id) {
        groupService.addUserToGroup(user_id, group_id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{group_id}/users/{user_id}")
    ResponseEntity<Object> removeUserFromGroup(@PathVariable("group_id") long group_id,
                                               @PathVariable("user_id") long user_id) {
        groupService.removeUserFromGroup(user_id, group_id);
        return ResponseEntity.ok().build();
    }

}
