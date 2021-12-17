package nl.serkanertas.filmspringserver.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateGroupPostRequest {
    @NotBlank
    @Size(min = 4, max = 32)
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
