package nl.serkanertas.filmspringserver.dto.response;

import nl.serkanertas.filmspringserver.model.Avatar;

import java.util.List;

public class GroupGetRequestDto {
    private String groupName;

    private boolean isWarned;

    private Avatar avatar;

    private List<SearchedUserGetRequestDto> usersInGroup;

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

    public List<SearchedUserGetRequestDto> getUsersInGroup() {
        return usersInGroup;
    }

    public void setUsersInGroup(List<SearchedUserGetRequestDto> usersInGroup) {
        this.usersInGroup = usersInGroup;
    }
}