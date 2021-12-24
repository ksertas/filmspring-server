package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.dto.response.*;
import nl.serkanertas.filmspringserver.model.*;
import nl.serkanertas.filmspringserver.service.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntityToDtoService {

    private final UserService userService;
    private final GroupService groupService;
    private final FilmService filmService;
    private final SeriesService seriesService;
    private final ActorService actorService;

    public EntityToDtoService(UserService userService,
                              GroupService groupService,
                              FilmService filmService,
                              SeriesService seriesService,
                              ActorService actorService) {
        this.userService = userService;
        this.groupService = groupService;
        this.filmService = filmService;
        this.seriesService = seriesService;
        this.actorService = actorService;
    }

    //    Users

    public SearchedUserGetRequest mapUserToSearchedUser(String user_id) {
        User user = userService.getUserEntity(user_id);
        SearchedUserGetRequest userDto = new SearchedUserGetRequest();
        userDto.setUsername(user.getUsername());
        userDto.setAvatar(user.getAvatarUser());
        if (!user.isMediaHidden()) {
            userDto.setWatchedFilms(user.getWatchedFilms());
            userDto.setWatchedSeries(user.getWatchedSeries());
            userDto.setPlannedFilms(user.getPlannedFlms());
            userDto.setPlannedSeries(user.getPlannedSeries());
        }
        userDto.setMediaHidden(user.isMediaHidden());
        return userDto;
    }

//    Groups

    public GroupGetRequest mapGroupToDto(Long group_id){
        Group group = groupService.getGroupEntity(group_id);
        GroupGetRequest groupDto = new GroupGetRequest();
        ArrayList<SearchedUserGetRequest> userGroupList = new ArrayList<>();
        groupDto.setGroupName(group.getName());
        groupDto.setAvatar(group.getAvatarGroup());

        for (User user : group.getUsersInGroup()) {
            userGroupList.add(userService.getSearchedUser(user.getUsername()));
        }
        groupDto.setUsersInGroup(userGroupList);
        groupDto.setPlannedFilms(group.getPlannedFlms());
        groupDto.setPlannedSeries(group.getPlannedSeries());
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

    public SeriesGetRequest SeriesGetRequest(long series_id) {
        Series series = seriesService.getSeriesEntity(series_id);
        SeriesGetRequest seriesDto = new SeriesGetRequest();
        List<ActorGetRequest> actorsList = new ArrayList<>();
        for (Actor actor : series.getActorsInSeries()) {
            actorsList.add(mapActorToDto(actor.getActor_id()));
        }
        seriesDto.setTitle(series.getTitle());
        seriesDto.setAlt_titles(series.getAlt_titles());
        seriesDto.setPlot(series.getPlot());
        seriesDto.setRuntime(String.valueOf(series.getRuntime()));
        seriesDto.setDirector(series.getDirector());
        seriesDto.setYearReleased(series.getYearReleased());
        seriesDto.setGenre(series.getGenre());
        seriesDto.setActors(actorsList);
        return seriesDto;
    }
}
