package nl.serkanertas.filmspringserver.service;

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
    private final FilmRatingService filmRatingService;
    private final SeriesRatingService seriesRatingService;

    public PostAuthService(@Lazy UserService userService,
                           @Lazy FilmService filmService,
                           @Lazy SeriesService seriesService,
                           @Lazy FilmRatingService filmRatingService,
                           @Lazy SeriesRatingService seriesRatingService) {
        this.userService = userService;
        this.filmService = filmService;
        this.seriesService = seriesService;
        this.filmRatingService = filmRatingService;
        this.seriesRatingService = seriesRatingService;
    }

    public boolean isGroupOwner(long group_id) {
        Collection<? extends GrantedAuthority> userAuthorities = SecurityContextHolder.getContext().
                getAuthentication().getAuthorities();
        String ownerGroupAuthority = "ROLE_OWNER-GROUP-";
        String groupIdPart = String.valueOf(group_id);
        String completeGroupAuthority = ownerGroupAuthority.concat(groupIdPart);
        return userAuthorities.contains(new SimpleGrantedAuthority(completeGroupAuthority));
    }

    public boolean isCurrentUserInGroup(long group_id) {
        Collection<? extends GrantedAuthority> userAuthorities = SecurityContextHolder.getContext().
                getAuthentication().getAuthorities();
        String ownerGroupAuthority = "ROLE_MEMBER-GROUP-";
        String groupIdPart = String.valueOf(group_id);
        String completeGroupAuthority = ownerGroupAuthority.concat(groupIdPart);
        return userAuthorities.contains(new SimpleGrantedAuthority(completeGroupAuthority));
    }

    public boolean currentUserIsInvited(long group_id) {
        Collection<? extends GrantedAuthority> userAuthorities = SecurityContextHolder.getContext().
                getAuthentication().getAuthorities();
        String ownerGroupAuthority = "ROLE_INVITED-";
        String groupIdPart = String.valueOf(group_id);
        String completeGroupAuthority = ownerGroupAuthority.concat(groupIdPart);
        return userAuthorities.contains(new SimpleGrantedAuthority(completeGroupAuthority));
    }

    public boolean isVerified() {
        String currentUserName = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return userService.getUserEntity(currentUserName).isVerified();
    }

    public boolean isCurrentUser(String user_id) {
        return SecurityContextHolder.getContext().getAuthentication()
                .getName().equals(user_id);
    }

    public boolean userHasWatchedFilm(String user_id, long film_id) {
        User user = userService.getUserEntity(user_id);
        Film film = filmService.getFilmEntity(film_id);
        return user.getWatchedFilms().contains(film);
    }

    public boolean userHasWatchedSeries(String user_id, long series_id) {
        User user = userService.getUserEntity(user_id);
        Series series = seriesService.getSeriesEntity(series_id);
        return user.getWatchedSeries().contains(series);
    }

}
