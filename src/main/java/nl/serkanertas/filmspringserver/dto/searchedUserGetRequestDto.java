package nl.serkanertas.filmspringserver.dto;

import nl.serkanertas.filmspringserver.model.Avatar;
import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Series;

import java.util.List;

public class searchedUserGetRequestDto {
    
    private String username;

    private Avatar avatar;

    private List<Film> watchedFilms;

    private List<Series> watchedSeries;

}
