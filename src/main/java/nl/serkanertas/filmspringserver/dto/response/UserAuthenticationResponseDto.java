package nl.serkanertas.filmspringserver.dto.response;

public class UserAuthenticationResponseDto {

    private final String jwt;

    public UserAuthenticationResponseDto(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}
