package nl.serkanertas.filmspringserver.dto.request;

import javax.validation.constraints.Size;

public class DeleteUserRequest {
    @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters long.")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
