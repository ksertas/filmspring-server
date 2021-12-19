package nl.serkanertas.filmspringserver.dto.response;

import nl.serkanertas.filmspringserver.model.Avatar;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Series;

import java.util.List;

public class SearchedUserGetRequest {
    
    private String username;

    private Avatar avatar;

    private boolean mediaHidden;

    private List<Film> watchedFilms;

    private List<Series> watchedSeries;

    private List<Film> plannedFilms;

    private List<Series> plannedSeries;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public boolean isMediaHidden() {
        return mediaHidden;
    }

    public void setMediaHidden(boolean mediaHidden) {
        this.mediaHidden = mediaHidden;
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
