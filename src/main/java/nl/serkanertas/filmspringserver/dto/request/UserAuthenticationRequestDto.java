package nl.serkanertas.filmspringserver.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserAuthenticationRequestDto {

    @NotBlank
    private String username;

    @Size(min=8)
    private String password;

    public UserAuthenticationRequestDto() {
    }
    public UserAuthenticationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
