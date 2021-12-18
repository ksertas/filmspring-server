package nl.serkanertas.filmspringserver.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Avatar avatarUser;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "hideMediaFromOthers")
    private boolean hideMedia;

    @ManyToMany(mappedBy = "usersWatchedFilm")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Film> watchedFilms = new ArrayList<>();

    @ManyToMany(mappedBy = "usersWatchedSeries")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Series> watchedSeries = new ArrayList<>();

    @ManyToMany(mappedBy = "usersInGroup")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Group> groupsUserIsIn = new ArrayList<>();

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

    public Avatar getAvatarUser() {
        return avatarUser;
    }

    public void setAvatarUser(Avatar avatar) {
        this.avatarUser = avatar;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isMediaHidden() {
        return hideMedia;
    }

    public void setMediaHidden(boolean hideMediaFromOthers) {
        this.hideMedia = hideMediaFromOthers;
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
