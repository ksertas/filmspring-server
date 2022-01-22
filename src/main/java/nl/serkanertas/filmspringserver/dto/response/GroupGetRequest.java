package nl.serkanertas.filmspringserver.dto.response;

import nl.serkanertas.filmspringserver.model.Avatar;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Series;

import java.util.List;

public class GroupGetRequest {

    private long id;

    private String groupName;

    private boolean isWarned;

    private Avatar avatar;

    private List<SearchedUserGetRequest> usersInGroup;

    private List<FilmGetRequest> plannedFilms;

    private List<SeriesGetRequest> plannedSeries;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isWarned() {
        return isWarned;
    }

    public void setWarned(boolean warned) {
        isWarned = warned;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public List<SearchedUserGetRequest> getUsersInGroup() {
        return usersInGroup;
    }

    public void setUsersInGroup(List<SearchedUserGetRequest> usersInGroup) {
        this.usersInGroup = usersInGroup;
    }

    public List<FilmGetRequest> getPlannedFilms() {
        return plannedFilms;
    }

    public void setPlannedFilms(List<FilmGetRequest> plannedFilms) {
        this.plannedFilms = plannedFilms;
    }

    public List<SeriesGetRequest> getPlannedSeries() {
        return plannedSeries;
    }

    public void setPlannedSeries(List<SeriesGetRequest> plannedSeries) {
        this.plannedSeries = plannedSeries;
    }
}