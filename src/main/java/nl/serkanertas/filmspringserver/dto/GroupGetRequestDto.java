package nl.serkanertas.filmspringserver.dto;

import nl.serkanertas.filmspringserver.model.Avatar;

import java.util.List;

public class GroupGetRequestDto {
    private String groupName;

    private boolean isWarned;

    private Avatar avatar;

    private List<searchedUserGetRequestDto> usersInGroup;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isWarned() {
        return isWarned;
    }

    public void setWarned(boolean warned) {
        isWarned = warned;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public List<searchedUserGetRequestDto> getUsersInGroup() {
        return usersInGroup;
    }

    public void setUsersInGroup(List<searchedUserGetRequestDto> usersInGroup) {
        this.usersInGroup = usersInGroup;
    }
}
