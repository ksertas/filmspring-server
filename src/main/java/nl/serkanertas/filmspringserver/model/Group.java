package nl.serkanertas.filmspringserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> usersInGroup = new ArrayList<>();

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
}
