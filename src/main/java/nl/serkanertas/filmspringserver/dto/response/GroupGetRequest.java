package nl.serkanertas.filmspringserver.dto.response;

import nl.serkanertas.filmspringserver.model.Avatar;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Series;

import java.util.List;

public class GroupGetRequest {
    private String groupName;

    private boolean isWarned;

    private Avatar avatar;

    private List<SearchedUserGetRequest> usersInGroup;

    private List<Film> plannedFilms;

    private List<Series> plannedSeries;

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

    public List<Film> getPlannedFilms() {
        return plannedFilms;
    }

    public void setPlannedFilms(List<Film> plannedFilms) {
        this.plannedFilms = plannedFilms;
    }

    public List<Series> getPlannedSeries() {
        return plannedSeries;
    }

    public void setPlannedSeries(List<Series> plannedSeries) {
        this.plannedSeries = plannedSeries;
    }
}