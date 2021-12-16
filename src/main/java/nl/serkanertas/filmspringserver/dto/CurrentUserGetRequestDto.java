package nl.serkanertas.filmspringserver.dto;

import nl.serkanertas.filmspringserver.model.Film;
import nl.serkanertas.filmspringserver.model.Group;
import nl.serkanertas.filmspringserver.model.Series;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class CurrentUserGetRequestDto {
    private String username;

    private String email;

    private boolean enabled;

    private MultipartFile avatar;

    private List<Film> watchedFilms;

    private List<Series> watchedSeries;

    private List<Group> groupsUserIsIn;
}
