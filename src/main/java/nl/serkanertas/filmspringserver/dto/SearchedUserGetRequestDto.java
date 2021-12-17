package nl.serkanertas.filmspringserver.dto;

import nl.serkanertas.filmspringserver.model.Avatar;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Series;

import java.util.List;

public class SearchedUserGetRequestDto {
    
    private String username;

    private Avatar avatar;

    private List<Film> watchedFilms;

    private List<Series> watchedSeries;

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
}
