package nl.serkanertas.filmspringserver.dto;

import nl.serkanertas.filmspringserver.model.Avatar;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class GroupPostRequestDto {
    @NotBlank
    @Size(min = 4, max = 32)
    private String groupName;

    private Avatar groupAvatar;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Avatar getGroupAvatar() {
        return groupAvatar;
    }

    public void setGroupAvatar(Avatar groupAvatar) {
        this.groupAvatar = groupAvatar;
    }
}
