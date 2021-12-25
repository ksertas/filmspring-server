package nl.serkanertas.filmspringserver.dto.response;

import nl.serkanertas.filmspringserver.model.Avatar;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Series;

import java.util.List;

public class SearchedUserGetRequest {
    
    private String username;

    private Avatar avatar;

    private boolean mediaHidden;

    private List<FilmGetRequest> watchedFilms;

    private List<SeriesGetRequest> watchedSeries;

    private List<FilmGetRequest> plannedFilms;

    private List<SeriesGetRequest> plannedSeries;

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
}
