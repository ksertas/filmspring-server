package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Rating;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.service.models.FilmService;
import nl.serkanertas.filmspringserver.service.models.RatingService;
import nl.serkanertas.filmspringserver.service.models.SeriesService;
import nl.serkanertas.filmspringserver.service.models.UserService;
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
    private final RatingService ratingService;

    public PostAuthService(@Lazy UserService userService,
                           @Lazy FilmService filmService,
                           @Lazy SeriesService seriesService,
                           @Lazy RatingService ratingService) {
        this.userService = userService;
        this.filmService = filmService;
        this.seriesService = seriesService;
        this.ratingService = ratingService;
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
        Rating rating = ratingService.getFilmRatingEntity(user_id, film_id);
        if (user.getWatchedFilms().contains(film)) {
            return true;
        } else {
            return false;
        }
    }

}
