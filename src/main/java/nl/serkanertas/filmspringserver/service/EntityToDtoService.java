package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.dto.response.GroupGetRequest;
import nl.serkanertas.filmspringserver.dto.response.SearchedUserGetRequest;
import nl.serkanertas.filmspringserver.model.Group;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.service.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        return groupDto;
    }
}
