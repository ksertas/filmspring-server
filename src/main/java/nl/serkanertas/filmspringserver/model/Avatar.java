package nl.serkanertas.filmspringserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "avatar")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long avatar_id;

    private String fileName;

    private String type;

    @Lob
    private byte[] data;

    @JsonIgnore
    @OneToOne(mappedBy = "avatarUser")
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "avatarGroup")
    private Group group;

    public Avatar(){}

    public Avatar(String name, String type, byte[] data) {
        this.fileName = name;
        this.type = type;
        this.data = data;
    }

    public long getAvatar_id() {
        return avatar_id;
    }

    public void setAvatar_id(long avatar_id) {
        this.avatar_id = avatar_id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
