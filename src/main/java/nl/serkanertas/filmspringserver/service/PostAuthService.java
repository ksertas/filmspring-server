package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.exception.UserNotAuthorizedException;
import nl.serkanertas.filmspringserver.model.*;
import nl.serkanertas.filmspringserver.service.models.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PostAuthService {

    private final UserService userService;
    private final FilmService filmService;
    private final SeriesService seriesService;

    public PostAuthService(@Lazy UserService userService,
                           @Lazy FilmService filmService,
                           @Lazy SeriesService seriesService) {
        this.userService = userService;
        this.filmService = filmService;
        this.seriesService = seriesService;
    }

    public boolean isCurrentUser(String user_id) {
        return SecurityContextHolder.getContext().getAuthentication()
                .getName().equals(user_id);
    }

    public boolean isGroupOwner(long group_id) {
        Collection<? extends GrantedAuthority> userAuthorities = SecurityContextHolder.getContext().
                getAuthentication().getAuthorities();
        String ownerGroupAuthority = "ROLE_OWNER-GROUP-";
        String groupIdPart = String.valueOf(group_id);
        String completeGroupAuthority = ownerGroupAuthority.concat(groupIdPart);
        if (userAuthorities.contains(new SimpleGrantedAuthority(completeGroupAuthority))) return true;
        else throw new UserNotAuthorizedException("user is not group owner");
    }

    public boolean isCurrentUserInGroup(long group_id) {
        Collection<? extends GrantedAuthority> userAuthorities = SecurityContextHolder.getContext().
                getAuthentication().getAuthorities();
        String ownerGroupAuthority = "ROLE_MEMBER-GROUP-";
        String groupIdPart = String.valueOf(group_id);
        String completeGroupAuthority = ownerGroupAuthority.concat(groupIdPart);
        if (userAuthorities.contains(new SimpleGrantedAuthority(completeGroupAuthority))) return true;
        else throw new UserNotAuthorizedException("user is not in group");
    }

    public boolean currentUserIsInvited(long group_id) {
        Collection<? extends GrantedAuthority> userAuthorities = SecurityContextHolder.getContext().
                getAuthentication().getAuthorities();
        String ownerGroupAuthority = "ROLE_INVITED-";
        String groupIdPart = String.valueOf(group_id);
        String completeGroupAuthority = ownerGroupAuthority.concat(groupIdPart);
        if (userAuthorities.contains(new SimpleGrantedAuthority(completeGroupAuthority))) return true;
        else throw new UserNotAuthorizedException();
    }

    public boolean isVerified() {
        String currentUserName = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        if (userService.getUserEntity(currentUserName).isVerified()) return true;
        else throw new UserNotAuthorizedException("User is not verified");
    }

    public boolean isVerified(String user_id) {
        String currentUserName = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        if (userService.getUserEntity(currentUserName).isVerified() &&
                isCurrentUser(user_id)) return true;
        else throw new UserNotAuthorizedException("User is not verified");
    }

    public boolean userHasWatchedFilm(String user_id, long film_id) {
        User user = userService.getUserEntity(user_id);
        Film film = filmService.getFilmEntity(film_id);
        if (user.getWatchedFilms().contains(film) && isCurrentUser(user_id)) return true;
        else throw new UserNotAuthorizedException("User has not watched film");
    }

    public boolean userHasWatchedSeries(String user_id, long series_id) {
        User user = userService.getUserEntity(user_id);
        Series series = seriesService.getSeriesEntity(series_id);
        if (user.getWatchedSeries().contains(series) && isCurrentUser(user_id)) return true;
        else throw new UserNotAuthorizedException("User has not watched series");
    }

}
