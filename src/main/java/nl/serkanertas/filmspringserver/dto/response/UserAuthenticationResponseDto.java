package nl.serkanertas.filmspringserver.dto.response;

public class UserAuthenticationResponseDto {
    private boolean auth = true;
    private final String jwt;

    public UserAuthenticationResponseDto(boolean auth, String jwt) {
        this.auth = auth;
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public boolean getAuth() {
        return auth;
    }

}
