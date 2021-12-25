package nl.serkanertas.filmspringserver.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PostAuthService {
    public boolean isGroupOwner(long group_id) {
        Collection<? extends GrantedAuthority> userAuthorities = SecurityContextHolder.getContext().
                getAuthentication().getAuthorities();
        System.out.println();
        String ownerGroupAuthority = "ROLE_OWNER-GROUP-";
        String groupIdPart = String.valueOf(group_id);
        String completeGroupAuthority = ownerGroupAuthority.concat(groupIdPart);
        return userAuthorities.contains(new SimpleGrantedAuthority(completeGroupAuthority));
    }
}
