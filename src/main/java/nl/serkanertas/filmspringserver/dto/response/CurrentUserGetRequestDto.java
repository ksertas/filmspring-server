package nl.serkanertas.filmspringserver.dto;

import nl.serkanertas.filmspringserver.model.Avatar;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Group;
import nl.serkanertas.filmspringserver.model.Series;
import java.util.List;

public class CurrentUserGetRequestDto {
    private String username;

    private String email;

    private boolean enabled;

    private Avatar avatar;

    private List<Film> watchedFilms;

    private List<Series> watchedSeries;

    private List<Group> groupsUserIsIn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public List<Film> getWatchedFilms() {
        return watchedFilms;
    }

    public void setWatchedFilms(List<Film> watchedFilms) {
        this.watchedFilms = watchedFilms;
    }

    public List<Series> getWatchedSeries() {
        return watchedSeries;
    }

    public void setWatchedSeries(List<Series> watchedSeries) {
        this.watchedSeries = watchedSeries;
    }

    public List<Group> getGroupsUserIsIn() {
        return groupsUserIsIn;
    }

    public void setGroupsUserIsIn(List<Group> groupsUserIsIn) {
        this.groupsUserIsIn = groupsUserIsIn;
    }
}
