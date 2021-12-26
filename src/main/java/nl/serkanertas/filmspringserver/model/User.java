package nl.serkanertas.filmspringserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_avatar",
    joinColumns = { @JoinColumn(name = "username", referencedColumnName = "username")},
    inverseJoinColumns = {@JoinColumn(name = "avatar_id", referencedColumnName = "avatar_id")})
    private Avatar avatarUser;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "is_verified")
    private boolean isVerified;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    @Column(name = "hideMediaFromOthers")
    private boolean hideMedia;

    @ManyToMany(mappedBy = "usersWatchedFilm")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Film> watchedFilms = new ArrayList<>();

    @ManyToMany(mappedBy = "usersWatchedSeries")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Series> watchedSeries = new ArrayList<>();

    @ManyToMany(mappedBy = "usersPlannedFilm")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Film> plannedFlms = new ArrayList<>();

    @ManyToMany(mappedBy = "usersPlannedSeries")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Series> plannedSeries = new ArrayList<>();

    @ManyToMany(mappedBy = "usersFavoriteFilm")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Film> favoriteFilms = new ArrayList<>();

    @ManyToMany(mappedBy = "usersFavoriteSeries")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Series> favoriteSeries = new ArrayList<>();

    @ManyToMany(mappedBy = "usersInGroup")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Group> groupsUserIsIn = new ArrayList<>();

    @ManyToMany
    private List<GroupInvitation> groupInvitations;

    public List<GroupInvitation> getGroupInvitations() {
        return groupInvitations;
    }

    public void setGroupInvitations(List<GroupInvitation> groupInvitations) {
        this.groupInvitations = groupInvitations;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "rating", fetch = FetchType.LAZY)
    private List<FilmRating> filmRatings;

    public List<FilmRating> getRatings() {
        return filmRatings;
    }

    public void setRatings(List<FilmRating> filmRatings) {
        this.filmRatings = filmRatings;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
    public void addAuthority(String authorityString) {
        this.authorities.add(new Authority(this.username, authorityString));
    }
    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
    }
    public void removeAuthority(String authorityString) {
        this.authorities.removeIf(authority -> authority.getAuthority().equalsIgnoreCase(authorityString));
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

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
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

    public List<Film> getFavoriteFilms() {
        return favoriteFilms;
    }

    public void setFavoriteFilms(List<Film> favoriteFilms) {
        this.favoriteFilms = favoriteFilms;
    }

    public List<Series> getFavoriteSeries() {
        return favoriteSeries;
    }

    public void setFavoriteSeries(List<Series> favoriteSeries) {
        this.favoriteSeries = favoriteSeries;
    }

    public void setWatchedSeries(List<Series> watchedSeries) {
        this.watchedSeries = watchedSeries;
    }

    public List<Film> getPlannedFlms() {
        return plannedFlms;
    }

    public void setPlannedFlms(List<Film> plannedFlms) {
        this.plannedFlms = plannedFlms;
    }

    public List<Series> getPlannedSeries() {
        return plannedSeries;
    }

    public void setPlannedSeries(List<Series> plannedSeries) {
        this.plannedSeries = plannedSeries;
    }

    public List<Group> getGroupsUserIsIn() {
        return groupsUserIsIn;
    }

    public void setGroupsUserIsIn(List<Group> groupsUserIsIn) {
        this.groupsUserIsIn = groupsUserIsIn;
    }
}
