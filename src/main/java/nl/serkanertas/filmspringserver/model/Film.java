package nl.serkanertas.filmspringserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long film_id;

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

    @Column(name = "runtime")
    private int runtime;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "film_poster",
            joinColumns = { @JoinColumn(name = "film_id", referencedColumnName = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "poster_id", referencedColumnName = "poster_id")})
    private Poster posterFilm;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "actor_film",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actorsInFilm = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_film",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "username")
    )
    private List<User> usersWatchedFilm = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_filmPL",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "username")
    )
    private List<User> usersPlannedFilm = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_filmFAV",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "username")
    )
    private List<User> usersFavoriteFilm = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "group_filmPL",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> groupPlannedFilm = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "rating", fetch = FetchType.LAZY)
    private List<FilmRating> filmRatings;

    public List<FilmRating> getRatings() {
        return filmRatings;
    }

    public void setRatings(List<FilmRating> filmRatings) {
        this.filmRatings = filmRatings;
    }

    public long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(long film_id) {
        this.film_id = film_id;
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

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Actor> getActorsInFilm() {
        return actorsInFilm;
    }

    public void setActorsInFilm(List<Actor> actorsInFilm) {
        this.actorsInFilm = actorsInFilm;
    }

    public List<User> getUsersWatchedFilm() {
        return usersWatchedFilm;
    }

    public List<User> getUsersFavoriteFilm() {
        return usersFavoriteFilm;
    }

    public void setUsersFavoriteFilm(List<User> usersFavoriteFilm) {
        this.usersFavoriteFilm = usersFavoriteFilm;
    }

    public void setUsersWatchedFilm(List<User> usersWatchedFilm) {
        this.usersWatchedFilm = usersWatchedFilm;
    }

    public List<User> getUsersPlannedFilm() {
        return usersPlannedFilm;
    }

    public void setUsersPlannedFilm(List<User> usersPlannedFilm) {
        this.usersPlannedFilm = usersPlannedFilm;
    }

    public List<Group> getGroupPlannedFilm() {
        return groupPlannedFilm;
    }

    public void setGroupPlannedFilm(List<Group> groupsPlannedFilm) {
        this.groupPlannedFilm = groupsPlannedFilm;
    }

    public Poster getPosterFilm() {
        return posterFilm;
    }

    public void setPosterFilm(Poster posterFilm) {
        this.posterFilm = posterFilm;
    }
}
