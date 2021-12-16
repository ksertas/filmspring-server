package nl.serkanertas.filmspringserver.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class GroupPostRequestDto {
    @NotBlank
    @Size(min = 4, max = 32)
    private String groupName;

    private MultipartFile groupAvatar;

    @Size(min = 0, max = 15)
    private List<String> invitedUsers;
}
