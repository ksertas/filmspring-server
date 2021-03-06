package nl.serkanertas.filmspringserver.dto.response;

import java.util.List;

public class FilmGetRequest {
    private long id;

    private String title;

    private String alt_titles;

    private String plot;

    private String genre;

    private String director;

    private String yearReleased;

    private String runtime;

    private List<ActorGetRequest> actors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlt_titles() {
        return alt_titles;
    }

    public void setAlt_titles(String alt_titles) {
        this.alt_titles = alt_titles;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(String yearReleased) {
        this.yearReleased = yearReleased;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public List<ActorGetRequest> getActors() {
        return actors;
    }

    public void setActors(List<ActorGetRequest> actors) {
        this.actors = actors;
    }
}
