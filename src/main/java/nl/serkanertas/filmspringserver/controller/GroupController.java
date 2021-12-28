package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.dto.request.CreateGroupPostRequest;
import nl.serkanertas.filmspringserver.service.InviteService;
import nl.serkanertas.filmspringserver.service.models.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;
    private final InviteService inviteService;

    public GroupController(GroupService groupService, InviteService inviteService) {
        this.groupService = groupService;
        this.inviteService = inviteService;
    }

    @GetMapping("/{group_id}")
    @PreAuthorize("@postAuthService.isCurrentUserInGroup(#group_id)")
    ResponseEntity<Object> getSearchedGroup(@PathVariable("group_id") long group_id) {
        return ResponseEntity.ok().body(groupService.getGroup(group_id));
    }

    @GetMapping
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> getAllGroups() {
        return ResponseEntity.ok().body(groupService.getAllGroups());
    }

    @GetMapping("/raw/{group_id}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> getGroupEntity(@PathVariable("group_id") long group_id) {
        return ResponseEntity.ok().body(groupService.getGroupEntity(group_id));
    }

    @PostMapping
    @PreAuthorize("@postAuthService.isVerified()")
    ResponseEntity<Object> createGroup(@Valid @RequestBody CreateGroupPostRequest group) throws IOException {
        groupService.createGroup(group);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{group_id}")
    @PreAuthorize("@postAuthService.isGroupOwner(#group_id) or hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> deleteGroup(@PathVariable("group_id") long group_id) {
        groupService.deleteGroup(group_id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{group_id}/users")
    @PreAuthorize("@postAuthService.isCurrentUserInGroup(#group_id)")
    ResponseEntity<Object> getAllUsersFromGroup(@PathVariable("group_id") long group_id) {
        return ResponseEntity.ok().body(groupService.getAllUsersFromGroup(group_id));
    }

    @PutMapping("/{group_id}/users/{user_id}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    ResponseEntity<Object> addUserToGroup(@PathVariable("group_id") long group_id,
                                          @PathVariable("user_id") String user_id) {
        groupService.addUserToGroup(user_id, group_id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{group_id}/users/{user_id}")
    @PreAuthorize("@postAuthService.isGroupOwner(#group_id)")
    ResponseEntity<Object> removeUserFromGroup(@PathVariable("group_id") long group_id,
                                               @PathVariable("user_id") String user_id) {
        groupService.removeUserFromGroup(user_id, group_id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{group_id}/invite/{user_id}")
    @PreAuthorize("@postAuthService.isCurrentUserInGroup(#group_id)")
    ResponseEntity<Object> inviteUserToGroup(@PathVariable("user_id") String user_id,
                                             @PathVariable("group_id") long group_id) {
        inviteService.inviteUser(user_id, group_id);
        return ResponseEntity.ok().body("Invite success");
    }

    @PutMapping("/{group_id}/invite/{user_id}/accept")
    @PreAuthorize("@postAuthService.currentUserIsInvited(#group_id)")
    ResponseEntity<Object> acceptInviteToGroup(@PathVariable("user_id") String user_id,
                                        @PathVariable("group_id") long group_id) {
        inviteService.acceptInvite(user_id, group_id);
        return ResponseEntity.ok().body("Accepted invite");
    }

    // TODO: preauthorize user with INVITED-id role
    @DeleteMapping("/{group_id}/invite/{user_id}/reject")
    @PreAuthorize("@postAuthService.currentUserIsInvited(#group_id)")
    ResponseEntity<Object> rejectInviteToGroup(@PathVariable("user_id") String user_id,
                                               @PathVariable("group_id") long group_id) {
        inviteService.rejectInvite(user_id, group_id);
        return ResponseEntity.ok().body("Rejected invite");
    }
}
