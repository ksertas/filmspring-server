package nl.serkanertas.filmspringserver.dto.response;

import nl.serkanertas.filmspringserver.model.Avatar;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Group;
import nl.serkanertas.filmspringserver.model.Series;
import java.util.List;

public class CurrentUserGetRequest {
    private String username;

    private String firstName;

    private String lastName;

    private String bio;

    private String email;

    private boolean enabled;

    private boolean isVerified;

    private boolean hideMediaFromOthers;

    private Avatar avatar;

    private List<FilmGetRequest> watchedFilms;

    private List<SeriesGetRequest> watchedSeries;

    private List<FilmGetRequest> plannedFilms;

    private List<SeriesGetRequest> plannedSeries;

    private List<FilmGetRequest> favoriteFilms;

    private List<SeriesGetRequest> favoriteSeries;

    private List<GroupGetRequest> groupsUserIsIn;

    private List<String> groupInvitationIds;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isHideMediaFromOthers() {
        return hideMediaFromOthers;
    }

    public void setHideMediaFromOthers(boolean hideMediaFromOthers) {
        this.hideMediaFromOthers = hideMediaFromOthers;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public List<FilmGetRequest> getWatchedFilms() {
        return watchedFilms;
    }

    public void setWatchedFilms(List<FilmGetRequest> watchedFilms) {
        this.watchedFilms = watchedFilms;
    }

    public List<SeriesGetRequest> getWatchedSeries() {
        return watchedSeries;
    }

    public void setWatchedSeries(List<SeriesGetRequest> watchedSeries) {
        this.watchedSeries = watchedSeries;
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

    public List<FilmGetRequest> getFavoriteFilms() {
        return favoriteFilms;
    }

    public void setFavoriteFilms(List<FilmGetRequest> favoriteFilms) {
        this.favoriteFilms = favoriteFilms;
    }

    public List<SeriesGetRequest> getFavoriteSeries() {
        return favoriteSeries;
    }

    public void setFavoriteSeries(List<SeriesGetRequest> favoriteSeries) {
        this.favoriteSeries = favoriteSeries;
    }

    public List<GroupGetRequest> getGroupsUserIsIn() {
        return groupsUserIsIn;
    }

    public void setGroupsUserIsIn(List<GroupGetRequest> groupsUserIsIn) {
        this.groupsUserIsIn = groupsUserIsIn;
    }

    public List<String> getGroupInvitationIds() {
        return groupInvitationIds;
    }

    public void setGroupInvitationIds(List<String> groupInvitationIds) {
        this.groupInvitationIds = groupInvitationIds;
    }
}
