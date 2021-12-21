package nl.serkanertas.filmspringserver.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class GroupInvitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invite_id;

    private long group_id;

    @ManyToMany
    @JoinTable(
            name = "user_invites",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "group_invitation_id")
    )
    private List<User> users;

    public long getInvite_id() {
        return invite_id;
    }

    public void setInvite_id(long invite_id) {
        this.invite_id = invite_id;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
