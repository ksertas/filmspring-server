package nl.serkanertas.filmspringserver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actor_id;
    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "actorsInFilm")
    private List<Film> films = new ArrayList<>();;

    @ManyToMany(mappedBy = "actorsInSeries")
    private List<Series> series = new ArrayList<>();
}
