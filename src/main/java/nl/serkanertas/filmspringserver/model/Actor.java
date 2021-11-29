package nl.serkanertas.filmspringserver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actor_id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToMany(mappedBy = "actorsInFilm")
    private Set<Film> films = new HashSet<>();;

    @ManyToMany(mappedBy = "actorsInSeries")
    private Set<Series> series = new HashSet<>();
}
