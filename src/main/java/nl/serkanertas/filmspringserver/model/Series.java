package nl.serkanertas.filmspringserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long series_id;

    @Column(name = "title")
    private String title;

    @Column(name = "alt_titles")
    private String alt_titles;

    @Column(name = "plot")
    private String plot;

    @Column(name = "genre")
    private String genre;

    @Column(name = "director")
    private String director;

    @Column(name = "year_released")
    private String yearReleased;

    @Column(name = "seasons")
    private int seasons;

    @Column(name = "runtime")
    private int runtime;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "actor_series",
            joinColumns = @JoinColumn(name = "series_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actorsInSeries = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_series",
            joinColumns = @JoinColumn(name = "series_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> usersWatchedSeries = new ArrayList<>();

    public long getSeries_id() {
        return series_id;
    }

    public void setSeries_id(long series_id) {
        this.series_id = series_id;
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

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Actor> getActorsInSeries() {
        return actorsInSeries;
    }

    public void setActorsInSeries(List<Actor> actorsInSeries) {
        this.actorsInSeries = actorsInSeries;
    }

    public List<User> getUsersWatchedSeries() {
        return usersWatchedSeries;
    }

    public void setUsersWatchedSeries(List<User> usersWatchedSeries) {
        this.usersWatchedSeries = usersWatchedSeries;
    }
}
