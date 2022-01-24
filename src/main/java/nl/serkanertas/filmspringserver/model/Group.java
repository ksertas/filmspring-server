package nl.serkanertas.filmspringserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long group_id;

    private String name;

    private boolean isWarned = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "group_avatar",
            joinColumns = { @JoinColumn(name = "group_id", referencedColumnName = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "avatar_id", referencedColumnName = "avatar_id")})
    private Avatar avatarGroup;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "username")
    )
    private List<User> usersInGroup = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "groupPlannedFilm")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Film> plannedFlms = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "groupPlannedSeries")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Series> plannedSeries = new ArrayList<>();

    private String groupOwnerName;

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public List<User> getUsersInGroup() {
        return usersInGroup;
    }

    public void setUsersInGroup(List<User> usersInGroup) {
        this.usersInGroup = usersInGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWarned() {
        return isWarned;
    }

    public void setWarned(boolean warned) {
        isWarned = warned;
    }

    public Avatar getAvatarGroup() {
        return avatarGroup;
    }

    public void setAvatarGroup(Avatar avatarGroup) {
        this.avatarGroup = avatarGroup;
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

    public String getGroupOwnerName() {
        return groupOwnerName;
    }

    public void setGroupOwnerName(String groupOwnerName) {
        this.groupOwnerName = groupOwnerName;
    }
}
