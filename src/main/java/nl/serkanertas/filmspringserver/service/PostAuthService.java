package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.service.models.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PostAuthService {

    private final UserService userService;

    public PostAuthService(UserService userService) {
        this.userService = userService;
    }

    public boolean isGroupOwner(long group_id) {
        Collection<? extends GrantedAuthority> userAuthorities = SecurityContextHolder.getContext().
                getAuthentication().getAuthorities();
        String ownerGroupAuthority = "ROLE_OWNER-GROUP-";
        String groupIdPart = String.valueOf(group_id);
        String completeGroupAuthority = ownerGroupAuthority.concat(groupIdPart);
        return userAuthorities.contains(new SimpleGrantedAuthority(completeGroupAuthority));
    }

    public boolean isVerified() {
        String currentUserName = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return userService.getUserEntity(currentUserName).isVerified();
    }
}
