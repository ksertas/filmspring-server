package nl.serkanertas.filmspringserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_avatar",
    joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "avatar_id", referencedColumnName = "avatar_id")})
    private Avatar avatar;

    @Column(name = "enabled")
    private boolean enabled;

//    @ManyToMany(mappedBy = "usersWatchedFilm")
//    private List<Film> watchedFilms = new ArrayList<>();
//
//    @ManyToMany(mappedBy = "usersWatchedSeries")
//    private List<Series> watchedSeries = new ArrayList<>();
//
//    @ManyToMany(mappedBy = "usersInGroup")
//    private List<Group> groupsUserIsIn = new ArrayList<>();

    @ManyToMany(mappedBy = "usersWatchedFilm")
    private Set<Film> watchedFilms = new HashSet<>();

    @ManyToMany(mappedBy = "usersWatchedSeries")
    private Set<Series> watchedSeries = new HashSet<>();

    @ManyToMany(mappedBy = "usersInGroup")
    private Set<Group> groupsUserIsIn = new HashSet<>();

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

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

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Film> getWatchedFilms() {
        return watchedFilms;
    }

    public void setWatchedFilms(Set<Film> watchedFilms) {
        this.watchedFilms = watchedFilms;
    }

    public Set<Series> getWatchedSeries() {
        return watchedSeries;
    }

    public void setWatchedSeries(Set<Series> watchedSeries) {
        this.watchedSeries = watchedSeries;
    }

    public Set<Group> getGroupsUserIsIn() {
        return groupsUserIsIn;
    }

    public void setGroupsUserIsIn(Set<Group> groupsUserIsIn) {
        this.groupsUserIsIn = groupsUserIsIn;
    }
}
