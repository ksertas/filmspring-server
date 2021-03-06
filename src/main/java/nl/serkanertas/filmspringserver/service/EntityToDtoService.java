package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.dto.response.*;
import nl.serkanertas.filmspringserver.model.*;
import nl.serkanertas.filmspringserver.service.models.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntityToDtoService {

    private final UserService userService;
    private final GroupService groupService;
    private final FilmService filmService;
    private final SeriesService seriesService;
    private final ActorService actorService;

    public EntityToDtoService(@Lazy UserService userService,
                              @Lazy GroupService groupService,
                              @Lazy FilmService filmService,
                              @Lazy SeriesService seriesService,
                              @Lazy ActorService actorService) {
        this.userService = userService;
        this.groupService = groupService;
        this.filmService = filmService;
        this.seriesService = seriesService;
        this.actorService = actorService;
    }

    //    Users

    @Transactional
    public CurrentUserGetRequest mapUserToCurrentUser(String user_id) {
        User user = userService.getUserEntity(user_id);
        CurrentUserGetRequest userDto = new CurrentUserGetRequest();
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBio(user.getBio());
        userDto.setAvatar(user.getAvatarUser());
        userDto.setEmail(user.getEmail());
        userDto.setEnabled(user.isEnabled());
        userDto.setVerified(user.isVerified());
        userDto.setGroupsUserIsIn(mapListOfGroupsToDto(user.getGroupsUserIsIn()));
        userDto.setGroupInvitationIds(user.getGroupInvitationIds());
        userDto.setHideMediaFromOthers(user.isMediaHidden());
        userDto.setWatchedFilms(mapListOfFilmsToDto(user.getWatchedFilms()));
        userDto.setPlannedFilms(mapListOfFilmsToDto(user.getPlannedFlms()));
        userDto.setWatchedSeries(mapListOfSeriesToDto(user.getWatchedSeries()));
        userDto.setPlannedSeries(mapListOfSeriesToDto(user.getPlannedSeries()));
        userDto.setFavoriteFilms(mapListOfFilmsToDto(user.getFavoriteFilms()));
        userDto.setFavoriteSeries(mapListOfSeriesToDto(user.getFavoriteSeries()));
        return userDto;
    }

    public SearchedUserGetRequest mapUserToSearchedUser(String user_id) {
        User user = userService.getUserEntity(user_id);
        SearchedUserGetRequest userDto = new SearchedUserGetRequest();
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBio(user.getBio());
        userDto.setAvatar(user.getAvatarUser());
        if (!user.isMediaHidden()) {
            userDto.setWatchedFilms(mapListOfFilmsToDto(user.getWatchedFilms()));
            userDto.setPlannedFilms(mapListOfFilmsToDto(user.getPlannedFlms()));
            userDto.setWatchedSeries(mapListOfSeriesToDto(user.getWatchedSeries()));
            userDto.setPlannedSeries(mapListOfSeriesToDto(user.getPlannedSeries()));
            userDto.setFavoriteFilms(mapListOfFilmsToDto(user.getFavoriteFilms()));
            userDto.setFavoriteSeries(mapListOfSeriesToDto(user.getFavoriteSeries()));
        }
        userDto.setMediaHidden(user.isMediaHidden());
        return userDto;
    }

//    Groups

    public GroupGetRequest mapGroupToDto(Long group_id){
        Group group = groupService.getGroupEntity(group_id);
        GroupGetRequest groupDto = new GroupGetRequest();
        ArrayList<SearchedUserGetRequest> userGroupList = new ArrayList<>();
        groupDto.setId(group.getGroup_id());
        groupDto.setGroupName(group.getName());
        groupDto.setGroupOwnerName(group.getGroupOwnerName());
        groupDto.setAvatar(group.getAvatarGroup());

        for (User user : group.getUsersInGroup()) {
            userGroupList.add(userService.getSearchedUser(user.getUsername()));
        }
        groupDto.setUsersInGroup(userGroupList);
        groupDto.setPlannedFilms(mapListOfFilmsToDto(group.getPlannedFlms()));
        groupDto.setPlannedSeries(mapListOfSeriesToDto(group.getPlannedSeries()));
        groupDto.setWarned(group.isWarned());
        return groupDto;
    }

    public ActorGetRequest mapActorToDto(long actor_id) {
        Actor actor = actorService.getActorEntity(actor_id);
        ActorGetRequest actorDto = new ActorGetRequest();
        actorDto.setFirstName(actor.getFirstName());
        actorDto.setLastName(actor.getLastName());
        return actorDto;
    }

    public FilmGetRequest mapFilmToDto(long film_id) {
        Film film = filmService.getFilmEntity(film_id);
        FilmGetRequest filmDto = new FilmGetRequest();
        List<ActorGetRequest> actorsList = new ArrayList<>();
        for (Actor actor : film.getActorsInFilm()) {
            actorsList.add(mapActorToDto(actor.getActor_id()));
        }
        filmDto.setId(film.getFilm_id());
        filmDto.setTitle(film.getTitle());
        filmDto.setAlt_titles(film.getAlt_titles());
        filmDto.setPlot(film.getPlot());
        filmDto.setRuntime(String.valueOf(film.getRuntime()));
        filmDto.setDirector(film.getDirector());
        filmDto.setYearReleased(film.getYearReleased());
        filmDto.setGenre(film.getGenre());
        filmDto.setActors(actorsList);
        return filmDto;
    }

    public SeriesGetRequest mapSeriesToDto(long series_id) {
        Series series = seriesService.getSeriesEntity(series_id);
        SeriesGetRequest seriesDto = new SeriesGetRequest();
        List<ActorGetRequest> actorsList = new ArrayList<>();
        for (Actor actor : series.getActorsInSeries()) {
            actorsList.add(mapActorToDto(actor.getActor_id()));
        }
        seriesDto.setId(series.getSeries_id());
        seriesDto.setTitle(series.getTitle());
        seriesDto.setAlt_titles(series.getAlt_titles());
        seriesDto.setPlot(series.getPlot());
        seriesDto.setRuntime(String.valueOf(series.getRuntime()));
        seriesDto.setDirector(series.getDirector());
        seriesDto.setYearReleased(series.getYearReleased());
        seriesDto.setSeasons(series.getSeasons());
        seriesDto.setGenre(series.getGenre());
        seriesDto.setActors(actorsList);
        return seriesDto;
    }

    public List<FilmGetRequest> mapListOfFilmsToDto(List<Film> films) {
        List<FilmGetRequest> toReturnList = new ArrayList<>();
        for (Film film : films) {
            toReturnList.add(mapFilmToDto(film.getFilm_id()));
        }
        return toReturnList;
    }

    public List<SeriesGetRequest> mapListOfSeriesToDto(List<Series> series) {
        List<SeriesGetRequest> toReturnList = new ArrayList<>();
        for (Series singularSeries : series) {
            toReturnList.add(mapSeriesToDto(singularSeries.getSeries_id()));
        }
        return toReturnList;
    }

    public List<GroupGetRequest> mapListOfGroupsToDto(List<Group> groups) {
        List<GroupGetRequest> toReturnList = new ArrayList<>();
        for (Group group : groups) {
            toReturnList.add(mapGroupToDto(group.getGroup_id()));
        }
        return toReturnList;
    }

}
