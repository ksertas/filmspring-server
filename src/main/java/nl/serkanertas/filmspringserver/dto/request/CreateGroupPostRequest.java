package nl.serkanertas.filmspringserver.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateGroupPostRequest {

    // Groupname regex from https://stackoverflow.com/a/12019115
    @NotBlank
    @Size(min = 4, max = 32)
    @Pattern(regexp = "^(?=.{3,32}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
